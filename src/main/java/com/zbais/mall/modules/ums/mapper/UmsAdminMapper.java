package com.zbais.mall.modules.ums.mapper;

import com.zbais.mall.modules.ums.model.UmsAdmin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 后台用户表 Mapper 接口
 * </p>
 *
 * @author Zbais
 * @since 2021-12-07
 */
@Mapper
public interface UmsAdminMapper extends BaseMapper<UmsAdmin> {
    /**
     * 获取资源相关用户ID列表
     * @param resourceId
     * @return
     */
    List<Long> getAdminIdList(@Param("resourcedId") Long resourceId);

}
