plugins {
  // Adds support for building a CLI application in Java.
  application

  id("org.graalvm.buildtools.native") version "0.10.6"
}

repositories {
  // Use Maven Central for resolving dependencies.
  mavenCentral()
}

dependencies {
  implementation("com.google.guava:guava:33.4.5-jre")

  // Use JUnit Jupiter for testing.
  testImplementation("org.junit.jupiter:junit-jupiter:5.12.1")

  testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

// Apply a specific Java toolchain to ease working on different environments.
java { toolchain { languageVersion = JavaLanguageVersion.of(24) } }

application {
  // Define the main class for the application.
  // IMPORTANT : Don't use the 'ö' character in package name.
  mainClass = "org.BifrostMesh.FeedsMicroservice.App"
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
