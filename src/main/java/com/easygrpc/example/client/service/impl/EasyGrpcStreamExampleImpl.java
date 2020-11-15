package com.easygrpc.example.client.service.impl;

import com.cht.easygrpc.spring.boot.annotation.EasyGrpcSpringService;
import com.cht.easygrpc.stream.EasyGrpcStreamObserver;
import com.easygrpc.example.client.service.EasyGrpcExample;
import com.easygrpc.example.client.service.EasyGrpcStreamExample;

/**
 * @author : chenhaitao934
 */

@EasyGrpcSpringService(interfaces = {EasyGrpcStreamExample.class})
public class EasyGrpcStreamExampleImpl implements EasyGrpcStreamExample {

    @Override
    public EasyGrpcStreamObserver<String> helloStream(EasyGrpcStreamObserver<String> request) {
        EasyGrpcStreamObserver<String> easyGrpcStreamObserver = new EasyGrpcStreamObserver<String>() {
            @Override
            public void onNext(String value) {
                System.out.println("receive client value: " + value);
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onCompleted() {
                request.onNext("server had completed client request...");
                request.onCompleted();

            }
        };
        return easyGrpcStreamObserver;
    }
}
