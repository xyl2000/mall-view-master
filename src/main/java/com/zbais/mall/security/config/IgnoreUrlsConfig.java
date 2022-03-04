package com.zbais.mall.security.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;


import java.util.ArrayList;
import java.util.List;

/**
 * 用于配置白名单资源路径
 * @author Zbais
 * create on: 2022/2/5
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "secure.ignored")
public class IgnoreUrlsConfig {

    private List<String> urls = new ArrayList<>();

}