package com.zbais.mall.modules.ums.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zbais.mall.modules.ums.model.UmsMenu;
import com.zbais.mall.modules.ums.model.UmsResource;
import com.zbais.mall.modules.ums.model.UmsRole;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Zbais
 * @since 2021-12-07
 * 后台角色管理
 */
public interface UmsRoleService extends IService<UmsRole> {
    /**
     * 添加角色
     * @param role 角色
     * @return 布尔值
     */
    boolean create(UmsRole role);

    /**
     * 批量删除角色
     * @param ids id
     * @return 布尔值
     */
    boolean delete(List<Long> ids);

    /**
     * 分页获取角色列表
     * @param keyword 关键字
     * @param pageSize 页大小
     * @param pageNum 页码
     * @return 角色列表
     */
    Page<UmsRole> list(String keyword, Integer pageSize, Integer pageNum);

    /**
     * 根据管理员id获取对应菜单
     * @param adminId 管理员id
     * @return 菜单列表
     */
    List<UmsMenu> getMenuList(Long adminId);

    /**
     * 获取角色相关菜单
     * @param roleId 角色id
     * @return 菜单集合
     */
    List<UmsMenu> listMenu(Long roleId);

    /**
     * 获取角色相关资源
     * @param roleId 角色id
     * @return 资源列表
     */
    List<UmsResource> listResource(Long roleId);

    /**
     * 给角色分配菜单
     * @param roleId 角色id
     * @param menuIds 菜单列表
     * @return 整型
     */
    @Transactional
    int allocMenu(Long roleId, List<Long> menuIds);

    /**
     * 给角色分配资源
     * @param roleId 角色id
     * @param resourceIds 资源id
     * @return 整型
     */
    @Transactional
    int allocResource(Long roleId, List<Long> resourceIds);
}
