package com.zbais.mall.modules.ums.controller;


import com.zbais.mall.common.api.CommonResult;
import com.zbais.mall.modules.ums.model.UmsMenu;
import com.zbais.mall.modules.ums.model.UmsResource;
import com.zbais.mall.modules.ums.model.UmsResourceCategory;
import com.zbais.mall.modules.ums.service.UmsResourceCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 资源分类表 前端控制器
 * </p>
 *
 * @author Zbais
 * @since 2021-12-07
 */
@RestController
@Api(tags = "UmsResourceCategoryController", description = "后台资源分类管理")
@RequestMapping("/resourceCategory")
public class UmsResourceCategoryController {
    @Resource
    private UmsResourceCategoryService resourceCategoryService;

    @ApiOperation(value = "添加后台资源分类")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public CommonResult<List<UmsResourceCategory>> listAll(){
        List<UmsResourceCategory> resourceList = resourceCategoryService.listAll();
        return CommonResult.success(resourceList);
    }

    @ApiOperation(value = "添加后台资源分类")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody UmsResourceCategory umsResourceCategory){
        boolean success = resourceCategoryService.create(umsResourceCategory);
        if(success){
            return CommonResult.success(null);
        }else{
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "修改后台资源分类")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Long id,
                               @RequestBody UmsResourceCategory umsResourceCategory){
        umsResourceCategory.setId(id);
        boolean success = resourceCategoryService.updateById(umsResourceCategory);
        if(success){
            return CommonResult.success(null);
        }else{
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "根据ID删除后台资源")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public CommonResult delete(@PathVariable Long id){
        boolean success = resourceCategoryService.removeById(id);
        if(success){
            return CommonResult.success(null);
        }else{
            return CommonResult.failed();
        }
    }
}

