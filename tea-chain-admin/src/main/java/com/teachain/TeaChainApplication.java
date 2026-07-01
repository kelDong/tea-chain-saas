package com.teachain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 */
@SpringBootApplication(scanBasePackages = "com.teachain")
public class TeaChainApplication {

    public static void main(String[] args) {
        SpringApplication.run(TeaChainApplication.class, args);
    }
}
