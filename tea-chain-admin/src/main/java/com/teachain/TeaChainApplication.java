package com.teachain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 启动类
 */
@SpringBootApplication(scanBasePackages = "com.teachain")
@EnableCaching
public class TeaChainApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeaChainApplication.class, args);
    }
}
