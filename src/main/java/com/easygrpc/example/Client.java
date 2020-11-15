package com.easygrpc.example;

import com.cht.easygrpc.spring.boot.annotation.EnableEasyGrpc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEasyGrpc
public class Client {

    public static void main(String[] args) {
        SpringApplication.run(Client.class, args);
    }

}
