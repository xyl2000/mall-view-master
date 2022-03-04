package com.zbais.mall.modules.ums.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zbais.mall.modules.ums.model.UmsResource;
import com.baomidou.mybatisplus.extension.service.IService;
import io.swagger.models.auth.In;

/**
 * @author Zbais
 * @since 2021-12-07
 * 后台资源管理
 */
public interface UmsResourceService extends IService<UmsResource> {
    /**
     * 添加资源
     * @param umsResource 资源
     * @return 布尔值
     */
    boolean create(UmsResource umsResource);

    /**
     * 修改资源
     * @param id id
     * @param umsResource 资源
     * @return 布尔值
     */
    boolean update(Long id, UmsResource umsResource);

    /**
     * 删除资源
     * @param id id
     * @return 布尔值
     */
    boolean delete(Long id);

    /**
     * 分页查询资源
     * @param categoryId 分类id
     * @param nameKeyword  姓名关键字
     * @param urlKeyword  路径关键字
     * @param pageSize 页大小
     * @param pageNum 页码
     * @return
     */
    Page<UmsResource> list(Long categoryId, String nameKeyword, String urlKeyword, Integer pageSize,Integer pageNum);

}
