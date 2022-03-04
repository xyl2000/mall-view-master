package com.zbais.mall.modules.ums.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zbais.mall.common.api.CommonPage;
import com.zbais.mall.common.api.CommonResult;
import com.zbais.mall.modules.ums.model.UmsResource;
import com.zbais.mall.modules.ums.service.UmsResourceService;
import com.zbais.mall.security.component.DynamicAccessDecisionManager;
import com.zbais.mall.security.component.DynamicSecurityMetadataSource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 后台资源表 前端控制器
 * </p>
 *
 * @author Zbais
 * @since 2021-12-07
 */
@RestController
@Api(tags = "UmsResourceConttroller", description = "后台资源管理")
@RequestMapping("/resource")
public class UmsResourceController {
    @Resource
    private UmsResourceService resourceService;
    @Resource
    private DynamicSecurityMetadataSource dynamicSecurityMetadataSource;

    @ApiOperation(value = "添加后台资源")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public CommonResult create(@RequestBody UmsResource umsResource){
        boolean success = resourceService.create(umsResource);
        dynamicSecurityMetadataSource.clearDataSource();
        if(success){
            return CommonResult.success(null);
        }else{
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "修改后台资源")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public CommonResult update(@PathVariable Long id,
                               @RequestBody UmsResource umsResource){
        boolean success = resourceService.update(id, umsResource);
        dynamicSecurityMetadataSource.clearDataSource();
        if(success){
            return CommonResult.success(null);
        }else{
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "根据ID获取资源详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CommonResult<UmsResource> getItem(@PathVariable Long id){
        UmsResource umsResource = resourceService.getById(id);
        return CommonResult.success(umsResource);
    }

    @ApiOperation(value = "根据ID删除后台资源")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public CommonResult delete(@PathVariable Long id){
        boolean success = resourceService.delete(id);
        dynamicSecurityMetadataSource.clearDataSource();
        if(success){
            return CommonResult.success(null);
        }else{
            return CommonResult.failed();
        }
    }


    public CommonResult<CommonPage<UmsResource>> list(@RequestParam(required = false) Long categoryId,
                                                      @RequestParam(required = false) String nameKeyword,
                                                      @RequestParam(required = false) String urlKeyword,
                                                      @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                      @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
        Page<UmsResource> resourceList = resourceService.list(categoryId, nameKeyword, urlKeyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(resourceList));
    }

    @ApiOperation(value = "查询所有后台资源")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    public CommonResult<List<UmsResource>> listAll(){
        List<UmsResource> resourceList = resourceService.list();
        return CommonResult.success(resourceList);
    }

}

