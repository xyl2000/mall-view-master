package com.zbais.mall.config;

import com.zbais.mall.common.config.BaseRedisConfig;
import com.zbais.mall.common.config.BaseSwaggerConfig;
import com.zbais.mall.common.domain.SwaggerProperties;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Swagger API文档相关配置
 * Created by Zbais on : 2021/12/11/21:33
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig extends BaseSwaggerConfig {
    @Override
    public SwaggerProperties swaggerProperties(){
        return SwaggerProperties.builder()
                .apiBasePackage("com.macro.mall.tiny.modules")
                .title("mall-view-master")
                .description("mall-view-master项目骨架相关接口文档")
                .contactName("mall_view")
                .version("1.0")
                .enableSecurity(true)
                .build();
    }
}
