package com.zbais.mall.config;

import com.zbais.mall.modules.ums.model.UmsResource;
import com.zbais.mall.modules.ums.service.UmsAdminService;
import com.zbais.mall.modules.ums.service.UmsResourceService;
import com.zbais.mall.security.component.DynamicSecurityService;
import com.zbais.mall.security.config.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Zbais
 * create on: 2022/2/7
 * mall-security模块相关配置
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MallSecurityConfig extends SecurityConfig {
    @Resource
    private UmsAdminService adminService;

    @Autowired
    private UmsResourceService resourceService;

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        return username -> adminService.loadUserByUsername(username);
    }

    public DynamicSecurityService dynamicSecurityService(){
        return new DynamicSecurityService() {
            @Override
            public Map<String, ConfigAttribute> loadDataSource() {
                Map<String, ConfigAttribute> map = new ConcurrentHashMap<>();
                List<UmsResource> resourceList = resourceService.list();
                for(UmsResource resource: resourceList){
                    map.put(resource.getUrl(), new org.springframework.security.access.SecurityConfig(resource.getId() + ":"+resource.getName()));
                }
                return map;
            }
        };

    }


}
