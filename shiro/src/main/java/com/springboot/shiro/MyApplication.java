package com.springboot.shiro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Created by shihao 2018/2/24 15:01
 */

@SpringBootApplication
@MapperScan("com.springboot.com.springboot.shiro.shiro.dao")
public class MyApplication extends SpringBootServletInitializer{

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(MyApplication.class);
        springApplication.setBannerMode(Banner.Mode.OFF);
        springApplication.run(args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder springApplicationBuilder){
        return  springApplicationBuilder.sources(MyApplication.class);
    }
}
