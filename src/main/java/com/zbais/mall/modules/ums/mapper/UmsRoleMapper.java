package com.zbais.mall.modules.ums.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zbais.mall.modules.ums.model.UmsMenu;
import com.zbais.mall.modules.ums.model.UmsResource;
import com.zbais.mall.modules.ums.model.UmsRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 后台用户角色表 Mapper 接口
 * </p>
 *
 * @author Zbais
 * @since 2021-12-07
 * 后台角色管理
 */
public interface UmsRoleMapper extends BaseMapper<UmsRole> {
    /**
     * 获取用户所有角色
     */
    List<UmsRole> getRoleList(@Param("adminId") Long adminId);
}
