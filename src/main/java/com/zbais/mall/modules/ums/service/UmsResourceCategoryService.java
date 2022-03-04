package com.zbais.mall.modules.ums.service;

import com.zbais.mall.modules.ums.model.UmsResourceCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author Zbais
 * @since 2021-12-07
 * 后台资源分类管理
 */
public interface UmsResourceCategoryService extends IService<UmsResourceCategory> {
    /**
     * 获取所有资源分类
     * @return 资源分类表
     */
    List<UmsResourceCategory> listAll();

    /**
     * 创建资源分类
     * @param umsResourceCategory 资源分类
     * @return 布尔值
     */
    boolean create(UmsResourceCategory umsResourceCategory);



}
