package com.easygrpc.example.client.service;

import com.cht.easygrpc.stream.EasyGrpcStreamObserver;

/**
 * @author : chenhaitao934
 */
public interface EasyGrpcStreamExample {

    EasyGrpcStreamObserver<String> helloStream(EasyGrpcStreamObserver<String> request);
}
