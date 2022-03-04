package com.zbais.mall.modules.ums.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zbais.mall.modules.ums.dto.UmsMenuNode;
import com.zbais.mall.modules.ums.model.UmsMenu;
import com.zbais.mall.modules.ums.mapper.UmsMenuMapper;
import com.zbais.mall.modules.ums.service.UmsMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 * 后台菜单表 服务实现类
 * </p>
 *
 * @author Zbais
 * @since 2021-12-07
 */
@Service
public class UmsMenuServiceImpl extends ServiceImpl<UmsMenuMapper, UmsMenu> implements UmsMenuService {

    @Override
    public boolean create(UmsMenu umsMenu) {
        umsMenu.setCreateTime(new Date());
        updateLevel(umsMenu);
        return save(umsMenu);
    }

    /**
     * 修改菜单层级
     * @param umsMenu 菜单
     */
    private void updateLevel(UmsMenu umsMenu){
        if(umsMenu.getParentId() == 0){
            // 没有父菜单时为一级菜单
            umsMenu.setLevel(0);
        }else{
            UmsMenu parentMenu = getById(umsMenu.getParentId());
            if(parentMenu != null){
                umsMenu.setLevel(parentMenu.getLevel()+1);
            }else{
                umsMenu.setLevel(0);
            }
        }
    }

    @Override
    public boolean update(Long id, UmsMenu umsMenu) {
        umsMenu.setId(id);
        updateLevel(umsMenu);
        return updateById(umsMenu);
    }

    @Override
    public Page<UmsMenu> list(Long parentId, Integer pageSize, Integer pageNum) {
        Page<UmsMenu> page = new Page<>(pageSize,pageNum);
        QueryWrapper<UmsMenu> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UmsMenu::getParentId, parentId)
                .orderByDesc(UmsMenu::getSort);
        return page(page,wrapper);
    }

    @Override
    public List<UmsMenuNode> treeList() {
        List<UmsMenu> menuList = list();
        List<UmsMenuNode> result = menuList.stream()
                .filter(menu -> menu.getParentId().equals(0L))
                .map(menu -> covertMenuNode(menu, menuList)).collect(Collectors.toList());
        return result;
    }

    /**
     * 将UmsMenu转化为UmsMenuNode并设置children属性
     * @param menu 菜单
     * @param menuList 菜单列表
     * @return 菜单节点
     */
    private UmsMenuNode covertMenuNode(UmsMenu menu, List<UmsMenu> menuList){
        UmsMenuNode node = new UmsMenuNode();
        BeanUtils.copyProperties(menu, node);
        List<UmsMenuNode> children = menuList.stream()
                .filter(subMenu -> subMenu.getParentId().equals(menu.getId()))
                .map(subMenu -> covertMenuNode(subMenu, menuList)).collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }

    @Override
    public boolean updateHidden(Long id, Integer hidden) {
        UmsMenu umsMenu = new UmsMenu();
        umsMenu.setId(id);
        umsMenu.setHidden(hidden);
        return updateById(umsMenu);
    }
}
