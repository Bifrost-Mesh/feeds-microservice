import com.google.protobuf.gradle.id

plugins {
  // Adds support for building a CLI application in Java.
  application

  alias(libs.plugins.graalvm.native)
  alias(libs.plugins.spring.boot)
  alias(libs.plugins.spring.dependency.management)
  alias(libs.plugins.protobuf)
}

repositories {
  mavenCentral()
}

dependencies {
  implementation(libs.guava)
  implementation(libs.spring.grpc.spring.boot.starter) // Provides a Netty based gRPC server.
                                                       // We can configure it from application.yml.
  implementation(libs.grpc.services)
  implementation(libs.protobuf.java)

  testImplementation(libs.junit.jupiter)
  testRuntimeOnly(libs.junit.platform.launcher)
  testImplementation(libs.spring.boot.starter.test)
  testImplementation(libs.spring.grpc.test)
}

protobuf {
  plugins {
    id("grpc") {
      artifact =
        libs.versions.grpc.get()
          .let { version -> "io.grpc:protoc-gen-grpc-java:$version" }
    }
  }

  generateProtoTasks {
    all().forEach {
      it.plugins {
        id("grpc") {
          option("jakarta_omit")
          option("@generated=omit")
        }
      }
    }
  }
}

graalvmNative {
  binaries.all {
    // To unlock experimental options.
    buildArgs.add("-H:+UnlockExperimentalVMOptions")

    // Points-to analysis is a crucial part of the GraalVM Native Image build process.
    // While Java projects might contain tens of thousands of classes coming from dependencies, we
    // can avoid compiling all the methods by analyzing which classes, methods, and fields that are
    // reachable, i.e., actually needed at runtime.
    //
    // SkipFlow is an extension of our points-to analysis that tracks primitive values and
    // evaluates branching conditions during the run of the analysis. It allows us to produce
    // ~6.35% smaller binaries without increasing the build time. In fact, image builds tend to be
    // even slightly faster with SkipFlow enabled because there are fewer methods to analyze and
    // compile.
    buildArgs.add("-O3")

    // You might know about ML-based profile inference in Native Image: in the absence of
    // user-supplied profiling information, Native Image in Oracle GraalVM uses a pre-trained ML
    // model to predict the execution probabilities of the control flow graph branches.
    // This enables powerful optimizations, improving the peak performance of native images out of
    // the box.
    //
    // Use the new generation of ML-enabled profile inference — GraalNN. With GraalNN, we are
    // observing a ~7.9% peak performance improvement on average.
    buildArgs.add("-H:+TrackPrimitiveValues")
    buildArgs.add("-H:+UsePredicates")
  }

  binaries {
    named("main") {
      // Specifies the name for the native executable file.
      // If a custom name is not supplied, the artifact ID of the project will be used by default
      // (defaults to the project name).
      imageName = "feeds-microservice"
    }
  }
}

// Apply a specific Java toolchain to ease working on different environments.
java {
  toolchain {
    languageVersion = JavaLanguageVersion.of(24)
  }
}

application {
  // Define the main class for the application.
  mainClass = "org.bifrostmesh.feedsmicroservice.App"
}
