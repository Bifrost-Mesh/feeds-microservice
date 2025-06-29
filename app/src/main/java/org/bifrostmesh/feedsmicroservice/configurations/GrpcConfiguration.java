package org.bifrostmesh.feedsmicroservice.configurations;

import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.grpc.server.ServerBuilderCustomizer;

import io.grpc.netty.NettyServerBuilder;

@Configuration(proxyBeanMethods = false)
public class GrpcConfiguration {

  @Bean
  public ServerBuilderCustomizer<NettyServerBuilder> nettyServerBuilderCustomizer() {
    return builder -> {
      // Use Java virtual threads for the embedded Netty server.
      builder.executor(Executors.newThreadPerTaskExecutor(Thread.ofVirtual().factory()));
    };
  }
}
