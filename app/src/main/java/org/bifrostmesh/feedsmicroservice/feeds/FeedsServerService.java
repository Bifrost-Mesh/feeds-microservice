package org.bifrostmesh.feedsmicroservice.feeds;

import org.bifrostmesh.feedsmicroservice.server.apis.feeds.proto.GetFeedRequest;
import org.bifrostmesh.feedsmicroservice.server.apis.feeds.proto.GetFeedResponse;
import org.bifrostmesh.feedsmicroservice.server.apis.feeds.proto.ReactorFeedsServiceGrpc.FeedsServiceImplBase;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.google.protobuf.Empty;

import io.grpc.Context;
import io.grpc.Status;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class FeedsServerService extends FeedsServiceImplBase {
  private FeedsService service;

  @Override
  public Mono<Empty> ping(Empty request) {
    return Mono.just(
      Empty.getDefaultInstance());
  }

  @Override
  public Mono<GetFeedResponse> getFeed(Mono<GetFeedRequest> requestMono) {
    return requestMono.zipWith(this.getUserIdFromContext(),
      (request, userId) -> {
        var pageRequest = PageRequest.of(
          (int) request.getPageRequest().getPageNumber(),
          (int) request.getPageRequest().getPageSize());

        return this.service.getFeed(userId, pageRequest);
      })
      .flatMap(postIdsFlux -> postIdsFlux.collectList())
      .map(postIds -> {
        return GetFeedResponse.newBuilder().addAllPostIds(postIds).build();
      });
  }

  private Mono<Integer> getUserIdFromContext() {
    var unparsedUserId = Context.key("user_id").get().toString();
    return Mono.just(unparsedUserId)
      .map(Integer::parseInt)
      .onErrorMap(error -> {
        return Status.FAILED_PRECONDITION
          .withDescription("Failed parsing user-id from gRPC metadata")
          .withCause(error)
          .asRuntimeException();
      });
  }
}
