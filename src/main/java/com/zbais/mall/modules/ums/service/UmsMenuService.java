package com.zbais.mall.modules.ums.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zbais.mall.modules.ums.dto.UmsMenuNode;
import com.zbais.mall.modules.ums.model.UmsMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @author Zbais
 * @since 2021-12-07
 * 后台菜单管理
 */
public interface UmsMenuService extends IService<UmsMenu> {
    /**
     * 创建后台菜单
     * @param umsMenu 菜单
     * @return 布尔值
     */
    boolean create(UmsMenu umsMenu);

    /**
     * 修改后台菜单
     * @param id id
     * @param umsMenu 菜单
     * @return 布尔值
     */
    boolean update(Long id, UmsMenu umsMenu);

    /**
     * 分页查询后台菜单
     * @param parentId 父id
     * @param pageSize 页大小
     * @param pageNum  页码
     * @return UmsMenu
     */
    Page<UmsMenu> list(Long parentId, Integer pageSize, Integer pageNum);

    /**
     * 树形结构返回所有菜单列表
     * @return UmsMenuNode
     */
    List<UmsMenuNode> treeList();

    /**
     * 修改菜单显示状态
     * @param id id
     * @param hidden 状态
     * @return 布尔值
     */
    boolean updateHidden(Long id, Integer hidden);



}
