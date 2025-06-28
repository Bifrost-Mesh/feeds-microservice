package org.bifrostmesh.feedsmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
  The SpringBootApplication annotation is a combination of 3 annotations :

    (1) @EnableAutoConfiguration: enable Spring Boot’s auto-configuration mechanism.

      Spring Boot auto-configuration attempts to automatically configure your Spring application
      based on the jar dependencies that you have added. For example, if HSQLDB is on your
      classpath, and you have not manually configured any database connection beans, then Spring
      Boot auto-configures an in-memory database.

      Auto-configuration is non-invasive. At any point, you can start to define your own
      configuration to replace specific parts of the auto-configuration. For example, if you add
      your own DataSource bean, the default embedded database support backs away.

      If you need to find out what auto-configuration is currently being applied, and why, start
      your application with the --debug switch.

    (2) @ComponentScan: enable @Component scan on the package where this application is located.

    (3) @SpringBootConfiguration: enable registration of extra beans in the context or the import
        of additional configuration classes.
*/
@SpringBootApplication
public class App {
  public static void main(String[] args) {
    // Bootstrap and run the Spring application.
    SpringApplication application = new SpringApplication(App.class);
    application.run(args);
  }
}
