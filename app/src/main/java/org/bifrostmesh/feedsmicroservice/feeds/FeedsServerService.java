package org.bifrostmesh.feedsmicroservice.feeds;

import org.bifrostmesh.feedsmicroservice.server.apis.feeds.proto.GetFeedRequest;
import org.bifrostmesh.feedsmicroservice.server.apis.feeds.proto.GetFeedResponse;
import org.bifrostmesh.feedsmicroservice.server.apis.feeds.proto.FeedsServiceGrpc.FeedsServiceImplBase;
import org.springframework.stereotype.Service;

import com.google.protobuf.Empty;

import io.grpc.stub.StreamObserver;

@Service
public class FeedsServerService extends FeedsServiceImplBase {

  @Override
  public void ping(Empty request, StreamObserver<Empty> responseObserver) {
    final var response = Empty.getDefaultInstance();

    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

  @Override
  public void getFeed(GetFeedRequest request, StreamObserver<GetFeedResponse> responseObserver) {
    // TODO Implement.
    super.getFeed(request, responseObserver);
  }
}
