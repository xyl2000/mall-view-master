package com.zbais.mall.modules.ums.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zbais.mall.modules.ums.model.UmsResourceCategory;
import com.zbais.mall.modules.ums.mapper.UmsResourceCategoryMapper;
import com.zbais.mall.modules.ums.service.UmsResourceCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 资源分类表 服务实现类
 * </p>
 *
 * @author Zbais
 * @since 2021-12-07
 */
@Service
public class UmsResourceCategoryServiceImpl extends ServiceImpl<UmsResourceCategoryMapper, UmsResourceCategory> implements UmsResourceCategoryService {

    @Override
    public List<UmsResourceCategory> listAll() {
        QueryWrapper<UmsResourceCategory> wrapper = new QueryWrapper<>();
        wrapper.lambda().orderByDesc(UmsResourceCategory::getSort);
        return list(wrapper);
    }

    @Override
    public boolean create(UmsResourceCategory umsResourceCategory) {
        umsResourceCategory.setCreateTime(new Date());
        return save(umsResourceCategory);
    }
}
