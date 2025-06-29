package org.bifrostmesh.feedsmicroservice.feeds;

import org.slf4j.Logger;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.ReactiveRedisOperations;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Repository
@Slf4j
@RequiredArgsConstructor
public class FeedsRedisRepository implements FeedsRepository {
  private Logger logger;

  private ReactiveRedisOperations<String, String> redis;

  public Flux<Integer> getFeed(Integer userId, PageRequest pageRequest) {
    String redisListKey = String.format("feeds:", userId);

    long start = pageRequest.getOffset(),
      end = start + pageRequest.getPageSize();

    return this.redis.opsForList()
      .range(redisListKey, start, end)
      .map(Integer::parseInt)
      .onErrorContinue((error, invalidPostId) -> {
        this.logger.atWarn()
          .setMessage("Failed parsing post-id")
          .setCause(error)
          .addKeyValue("post-id", invalidPostId);
      });
  }
}
