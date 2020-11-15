package com.easygrpc.example.client.service.impl;

import com.cht.easygrpc.spring.boot.annotation.EasyGrpcSpringService;
import com.cht.easygrpc.stream.EasyGrpcStreamObserver;
import com.easygrpc.example.client.service.EasyGrpcExample;

/**
 * @author : chenhaitao934
 */
@EasyGrpcSpringService(interfaces = {EasyGrpcExample.class})
public class EasyGrpcExampleImpl implements EasyGrpcExample {
    @Override
    public String hello(String req) {
        return "hello: " + req;
    }

}
