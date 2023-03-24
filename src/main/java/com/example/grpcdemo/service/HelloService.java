package com.example.grpcdemo.service;

import com.example.grpcdemo.DataResponse;
import com.example.grpcdemo.HelloRequest;
import com.example.grpcdemo.HelloResponse;
import com.example.grpcdemo.ScoreServiceGrpc;
import com.example.grpcdemo.jpa.DataEntityRepository;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@GRpcService
@RequiredArgsConstructor
public class HelloService extends ScoreServiceGrpc.ScoreServiceImplBase {

    private final DataEntityRepository dataEntityRepository;

    @Override
    @Transactional(readOnly = true)
    public void list(HelloRequest request, StreamObserver<HelloResponse> responseObserver) {
        int index = request.getFetch();
        Pageable pageable = Pageable.ofSize(100);

        for (int i = 0; i < (index / 100); i++) {
            HelloResponse.Builder builder = HelloResponse.newBuilder();
            dataEntityRepository.findAll(pageable.withPage(i))
                    .stream()
                    .parallel()
                    .map(dataEntity -> DataResponse.newBuilder()
                            .setData(dataEntity.getData())
                            .setId(dataEntity.getId().intValue())
                            .build()
                    ).forEach(builder::addLists);
            responseObserver.onNext(builder.build());
        }
        responseObserver.onCompleted();
    }
}
