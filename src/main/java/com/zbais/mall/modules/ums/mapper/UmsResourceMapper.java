package com.zbais.mall.modules.ums.mapper;

import com.zbais.mall.modules.ums.model.UmsResource;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 后台资源表 Mapper 接口
 * </p>
 *
 * @author Zbais
 * @since 2021-12-07
 * 后台资源表 Mapper 接口
 */
public interface UmsResourceMapper extends BaseMapper<UmsResource> {
    /**
     * 获取用户所有可访问的资源
     * @param adminId
     * @return
     */
    List<UmsResource> getResourceList(@Param("adminId") Long adminId);

    List<UmsResource> getResourceByRoleId(@Param("roleId") Long roleId);



}
