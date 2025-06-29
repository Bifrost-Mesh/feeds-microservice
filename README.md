# Feeds Microservice

![Spring Badge](https://img.shields.io/badge/Spring-6DB33F?logo=spring&logoColor=fff&style=for-the-badge)
![Spring Boot Badge](https://img.shields.io/badge/Spring%20Boot-6DB33F?logo=springboot&logoColor=fff&style=for-the-badge)
![Gradle Badge](https://img.shields.io/badge/Gradle-02303A?logo=gradle&logoColor=fff&style=for-the-badge)

## Development Environment setup

Prerequisites :

- [Nix](https://github.com/DeterminateSystems/nix-installer)
- [Direnv](https://direnv.net/)

Once you have them installed :

1. Allow `direnv` to automatically land you into the **Nix development shell environment**, whenever you cd into this directory :
    ```shell script
    direnv allow
    ```

2. Generate Java code from the [protobuf definitions](./app/src/main/proto) by running :
    ```shell script
    gradle generateProto
    ```

## Neovim setup

I'm using [LazyVim's Java preset](https://www.lazyvim.org/extras/lang/java).

> If you're using Neovim, then seldom there will be some libraries which will not get added to the classpath automatically.
> Running `gradle eclipse --no-configuration-cache` will fix the issue.
> The [app/.classpath](./app/.classpath), which JDTLS relies on to provide you autocompletion, will get updated 😃.

I don't have the time and luxury to figure out **how to specify project formatting options** to JDTLS 🙂. For now, I've added the following in **~/.cache/nvim/jdtls/feeds-microservice/workspace/.metadata/.plugins/org.eclipse.core.runtime/.settings/org.eclipse.jdt.core.prefs** :

```prefs
org.eclipse.jdt.core.formatter.continuation_indentation=1
org.eclipse.jdt.core.formatter.continuation_indentation_for_array_initializer=1

org.eclipse.jdt.core.formatter.comment.format_block_comments=false
org.eclipse.jdt.core.formatter.comment.format_javadoc_comments=false

org.eclipse.jdt.core.formatter.comment.format_line_comments=false
```

## REFERENCEs

**Java** :

- [Modern Java Deep Dive](https://www.youtube.com/watch?v=z4qsidg261E)

- [Now Is The Best Time To Learn Java](https://www.youtube.com/watch?v=tbXxaniKjDg)

- [What is the difference between JDK and JRE?](https://stackoverflow.com/questions/1906445/what-is-the-difference-between-jdk-and-jre)

- [What is a shaded JAR file? And what is the difference/similarities between an uber JAR and shaded JAR?](https://stackoverflow.com/questions/49810578/what-is-a-shaded-jar-file-and-what-is-the-difference-similarities-between-an-ub)

- [Virtual Threads](https://docs.oracle.com/en/java/javase/21/core/virtual-threads.html)

**Gradle** and **GraalVM** :

- [Gradle Beginner Tutorial : Intiliazing the Project](https://docs.gradle.org/current/userguide/part1_gradle_init.html)

- [Gradle : Version Catalogs](https://docs.gradle.org/current/userguide/version_catalogs.html#version-catalog)

- [Improve the Performance of Gradle Builds](https://docs.gradle.org/current/userguide/performance.html)

- [Welcome, GraalVM for JDK 24!🚀](https://medium.com/graalvm/welcome-graalvm-for-jdk-24-7c829fe98ea1)

- [Building Native Images with Gradle: An End-to-End Guide](https://graalvm.github.io/native-build-tools/latest/end-to-end-gradle-guide.html)

- [Make native-gradle-plugin compatible with Gradle's configuration cache](https://github.com/graalvm/native-build-tools/issues/477)

**Protocol Buffers** and **gRPC** :

- [Protocol Buffers Documentation : Style Guide](https://protobuf.dev/programming-guides/style)

- [Protocol Buffers Documentation : Java Generated Code Guide](https://protobuf.dev/reference/java/java-generated/)

**Spring Ecosystem** :

- [Spring Boot with Netty vs. Spring Boot with Jetty — Embedded Java Servers](https://medium.com/@a.kago1988/spring-boot-with-netty-vs-jetty-the-battle-of-embedded-servers-99027eb82cfd)

- [Netty data model, threading, and gotchas](https://medium.com/@akhaku/netty-data-model-threading-and-gotchas-cab820e4815a)

- [Structuring Your Code](https://docs.spring.io/spring-boot/reference/using/structuring-your-code.html)

- [Spring Beans Showdown: Unraveling the Mystery of @Component vs @Bean!](https://www.youtube.com/watch?v=CWEQ-1vff1o)

- [Spring @Configuration Annotation - What are Proxy Bean Methods?](https://www.youtube.com/watch?v=VoK6-OiSPu4)

- [Spring Tips: Spring Modulith ](https://www.youtube.com/watch?v=MYEx0kO2-8A)

- [Developing reactive application with Spring WebFlux and Spring Data Redis - Part 1 of 2](https://www.youtube.com/watch?v=KrxXdnCxiFg)

- [Simple Spring Boot Pagination With Pageable & Data Repositories](https://www.youtube.com/watch?v=Z0_6zg0Xcls)

- [#7 Autowire using Spring Boot ](https://www.youtube.com/watch?v=ET39IFffr24)

- [Structured logging in Spring Boot 3.4](https://spring.io/blog/2024/08/23/structured-logging-in-spring-boot-3-4)

- [Working with Objects through RedisTemplate](https://docs.spring.io/spring-data/redis/reference/redis/template.html)

**Reactive programming** :

- [Reactor in Action - Simon Baslé, Victor Grazi](https://www.youtube.com/watch?v=kwuu1efzkf4)

- [30 Error handling in reactive programming (Reactive programming with Java - full course)](https://www.youtube.com/watch?v=xzn2KbmkcPE)
