package com.t0r.pixelarkbackend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@MapperScan("com.t0r.pixelarkbackend.mapper")
@EnableAspectJAutoProxy(exposeProxy = true)
public class PixelarkBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(PixelarkBackendApplication.class, args);
    }

}
