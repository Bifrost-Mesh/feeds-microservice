package org.bifrostmesh.feedsmicroservice.feeds;

import org.springframework.data.domain.PageRequest;

import reactor.core.publisher.Flux;

public interface FeedsRepository {
  public Flux<Integer> getFeed(Integer userId, PageRequest pageRequest);
}
