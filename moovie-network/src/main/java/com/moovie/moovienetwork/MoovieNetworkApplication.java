package com.moovie.moovienetwork;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MoovieNetworkApplication {

    public static void main(String[] args) {
        SpringApplication.run(MoovieNetworkApplication.class, args);
    }

}
