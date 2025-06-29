package org.bifrostmesh.feedsmicroservice.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;

@Configuration(proxyBeanMethods = false)
public class RedisConfiguration {

  @Bean
  public LettuceConnectionFactory redisConnectionFactory() {
    return new LettuceConnectionFactory(new RedisStandaloneConfiguration());
  }

  // The ReactiveRedisTemplate class implements the ReactiveRedisOperations
  // interface.
  // Once configured, the template is thread-safe and can be reused across
  // multiple instances.
  @Bean
  ReactiveRedisTemplate<String, String> reactiveRedisTemplate(
      ReactiveRedisConnectionFactory connectionFactory) {

    return new ReactiveRedisTemplate<>(connectionFactory, RedisSerializationContext.string());
  }
}
