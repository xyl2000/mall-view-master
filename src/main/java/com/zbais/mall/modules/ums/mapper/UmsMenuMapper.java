package com.zbais.mall.modules.ums.mapper;

import com.zbais.mall.modules.ums.model.UmsMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 后台菜单表 Mapper 接口
 * </p>
 *
 * @author Zbais
 * @since 2021-12-07
 */
public interface UmsMenuMapper extends BaseMapper<UmsMenu> {

    /**
     * 根据后台用户ID获取菜单
     * @param adminId
     * @return
     */
    List<UmsMenu> getMenuList(@Param("adminId") Long adminId);

    /**
     * 根据角色ID获取菜单
     * @param roleId
     * @return
     */
    List<UmsMenu> getMenuByRoleId(@Param("roleId") Long roleId );


}
