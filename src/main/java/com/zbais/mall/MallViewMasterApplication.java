package com.zbais.mall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

/**
 * @author zbais
 */
@SpringBootApplication
@MapperScan(value = "com.zbais.mall.modules.ums.mapper")
public class MallViewMasterApplication {

    public static void main(String[] args) {
        SpringApplication.run(MallViewMasterApplication.class, args);
    }

}
