package org.bifrostmesh.feedsmicroservice.feeds;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class FeedsService {

  private final FeedsRedisRepository repository;

  public Flux<Integer> getFeed(Integer userId, PageRequest pageRequest) {
    return this.repository.getFeed(userId, pageRequest);
  }
}
