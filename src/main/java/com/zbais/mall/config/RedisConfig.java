package com.zbais.mall.config;

import com.zbais.mall.common.config.BaseRedisConfig;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * Redis 配置类
 * Created by Zbais on : 2021/12/11/21:09
 * @author Zbais
 */
@EnableCaching
@Configuration
public class RedisConfig extends BaseRedisConfig {
}
