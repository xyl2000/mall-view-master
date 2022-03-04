package com.zbais.mall.modules.ums.service;

import com.zbais.mall.modules.ums.model.UmsAdmin;
import com.zbais.mall.modules.ums.model.UmsResource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Zbais
 * create on: 2022/2/8
 * 后台用户缓存管理
 */
@Service
public interface UmsAdminCacheService {
    /**
     * 删除后台用户缓存
     * @param adminId 管理员id
     */
    void delAdmin(Long adminId);

    /**
     * 删除后台用户资源列表缓存
     * @param adminId 管理员id
     */
    void delResourceList(Long adminId);

    /**
     * 当角色相关资源信息改变时删除相关后台用户缓存
     * @param roleId 角色id
     */
    void delResourceListByRole(Long roleId);

    /**
     * 当角色相关资源信息改变时删除相关后台用户缓存
     * @param roleIds 角色id
     */
    void delResourceListByRoleIds(List<Long> roleIds);

    /**
     * 当资源信息改变时， 删除资源项目后台用户缓存
     * @param resourceId 资源id
     */
    void delResourceListByResource(Long resourceId);

    /**
     * 获取缓存后台用户信息
     * @param username 用户名
     * @return UmsAdmin
     */
    UmsAdmin getAdmin(String username);

    /**
     * 设置缓存后台信息
     * @param admin 管理员
     */
    void setAdmin(UmsAdmin admin);

    /**
     * 获取缓存后台用户资源列表
     * @param adminId 管理员id
     * @return UmsResource
     */
    List<UmsResource> getResourceList(Long adminId);

    /**
     * 设置后台用户资源列表
     * @param adminId 管理员id
     * @param resourceList 资源列表
     */
    void setResourceList(Long adminId, List<UmsResource> resourceList);

}
