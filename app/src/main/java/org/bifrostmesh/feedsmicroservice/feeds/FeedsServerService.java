package org.bifrostmesh.feedsmicroservice.feeds;

import org.bifrostmesh.feedsmicroservice.server.apis.feeds.proto.GetFeedRequest;
import org.bifrostmesh.feedsmicroservice.server.apis.feeds.proto.GetFeedResponse;
import org.bifrostmesh.feedsmicroservice.server.apis.feeds.proto.ReactorFeedsServiceGrpc.FeedsServiceImplBase;
import org.springframework.stereotype.Service;

import com.google.protobuf.Empty;

import reactor.core.publisher.Mono;

@Service
public class FeedsServerService extends FeedsServiceImplBase {

  @Override
  public Mono<Empty> ping(Empty request) {
    return Mono.just(
        Empty.getDefaultInstance());
  }

  @Override
  public Mono<GetFeedResponse> getFeed(Mono<GetFeedRequest> request) {
    // TODO Implement.
    return super.getFeed(request);
  }
}
