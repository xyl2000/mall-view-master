package com.zbais.mall.modules.ums.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zbais.mall.modules.ums.mapper.UmsMenuMapper;
import com.zbais.mall.modules.ums.mapper.UmsResourceMapper;
import com.zbais.mall.modules.ums.model.*;
import com.zbais.mall.modules.ums.mapper.UmsRoleMapper;
import com.zbais.mall.modules.ums.service.UmsAdminCacheService;
import com.zbais.mall.modules.ums.service.UmsRoleMenuRelationService;
import com.zbais.mall.modules.ums.service.UmsRoleResourceRelationService;
import com.zbais.mall.modules.ums.service.UmsRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 后台用户角色表 服务实现类
 * </p>
 *
 * @author Zbais
 * @since 2021-12-07
 */
@Service
public class UmsRoleServiceImpl extends ServiceImpl<UmsRoleMapper, UmsRole> implements UmsRoleService {

    @Autowired
    private UmsAdminCacheService adminCacheService;
    @Resource
    private UmsMenuMapper menuMapper;
    @Resource
    private UmsResourceMapper resourceMapper;
    @Resource
    private UmsRoleMenuRelationService roleMenuRelationService;
    @Resource
    private UmsRoleResourceRelationService roleResourceRelationService;

    @Override
    public boolean create(UmsRole role) {
        role.setCreateTime(new Date());
        role.setAdminCount(0);
        role.setSort(0);
        return save(role);
    }

    @Override
    public boolean delete(List<Long> ids) {
        boolean success = removeByIds(ids);
        adminCacheService.delResourceListByRoleIds(ids);
        return success;
    }

    @Override
    public Page<UmsRole> list(String keyword, Integer pageSize, Integer pageNum) {
        Page<UmsRole> page = new Page<>(pageNum, pageSize);
        QueryWrapper<UmsRole> wrapper = new QueryWrapper<>();
        LambdaQueryWrapper<UmsRole> lambda = wrapper.lambda();
        if(StrUtil.isNotEmpty(keyword)){
            lambda.eq(UmsRole::getName,keyword);
        }
        return page(page, wrapper);
    }

    @Override
    public List<UmsMenu> getMenuList(Long adminId) {
        return menuMapper.getMenuList(adminId);
    }

    @Override
    public List<UmsMenu> listMenu(Long roleId) {
        return menuMapper.getMenuByRoleId(roleId);
    }

    @Override
    public List<UmsResource> listResource(Long roleId) {
        return resourceMapper.getResourceByRoleId(roleId);
    }

    @Override
    public int allocMenu(Long roleId, List<Long> menuIds) {
        // 先删除原有关系
        QueryWrapper<UmsRoleMenuRelation> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UmsRoleMenuRelation::getId, roleId);
        roleMenuRelationService.remove(wrapper);
        // 批量插入新关系
        List<UmsRoleMenuRelation> relationList = new ArrayList<>();
        for(Long menuId : menuIds){
            UmsRoleMenuRelation relation = new UmsRoleMenuRelation();
            relation.setRoleId(roleId);
            relation.setMenuId(menuId);
            relationList.add(relation);
        }
        roleMenuRelationService.saveBatch(relationList);
        return menuIds.size();
    }

    @Override
    public int allocResource(Long roleId, List<Long> resourceIds) {
        // 先删除原有关系
        QueryWrapper<UmsRoleResourceRelation> wrapper =  new QueryWrapper<>();
        wrapper.lambda().eq(UmsRoleResourceRelation::getRoleId, roleId);
        roleResourceRelationService.remove(wrapper);
        // 批量插入新关系
        List<UmsRoleResourceRelation> relationList = new ArrayList<>();
        for(Long resourceId : resourceIds){
            UmsRoleResourceRelation relation = new UmsRoleResourceRelation();
            relation.setResourceId(resourceId);
            relation.setRoleId(roleId);
            relationList.add(relation);
        }
        roleResourceRelationService.saveBatch(relationList);
        adminCacheService.delResourceListByRole(roleId);
        return resourceIds.size();
    }
}
