package com.easygrpc.example.client.controller;

import com.cht.easygrpc.spring.boot.annotation.EasyGrpcAutowired;
import com.cht.easygrpc.spring.boot.config.EasyGrpcConstants;
import com.cht.easygrpc.stream.AbstractEasyGrpcStreamObserver;
import com.cht.easygrpc.stream.EasyGrpcStreamObserver;
import com.easygrpc.example.client.service.EasyGrpcExample;
import com.easygrpc.example.client.service.EasyGrpcStreamExample;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;

/**
 * @author : chenhaitao934
 */
@RestController
public class EasyGrpcController {

    @EasyGrpcAutowired
    private EasyGrpcExample easyGrpcExample;

    @EasyGrpcAutowired(type = EasyGrpcConstants.EASY_GRPC_TYPE_STREAM)
    private EasyGrpcStreamExample streamExample;

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return easyGrpcExample.hello("easy grpc");
    }

    @RequestMapping("/helloStream")
    @ResponseBody
    public String helloStream() throws InterruptedException {
        final CountDownLatch done = new CountDownLatch(1);


        // 定义服务端返回结果的回调
        AbstractEasyGrpcStreamObserver<String> clientStreamObserver = new AbstractEasyGrpcStreamObserver<String>() {
            @Override
            public void onNext(String value) {
                System.out.println("receive server response: " + value);
            }

            @Override
            public void onError(Throwable t) {
                t.printStackTrace();
            }

            @Override
            public void onCompleted() {
                System.out.println("stream onCompleted");
                done.countDown();
            }
        };

        EasyGrpcStreamObserver<String> serverStreamObserver = streamExample.helloStream(clientStreamObserver);
        for(int i = 0; i < 10; i++){
            serverStreamObserver.onNext(i + "");
        }
        serverStreamObserver.onCompleted();
        done.await();
        return "success";
    }

}
