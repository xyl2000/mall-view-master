package com.zbais.mall.modules.ums.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zbais.mall.common.api.CommonPage;
import com.zbais.mall.common.api.CommonResult;
import com.zbais.mall.modules.ums.dto.UmsMenuNode;
import com.zbais.mall.modules.ums.model.UmsMenu;
import com.zbais.mall.modules.ums.service.UmsMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 后台菜单表 前端控制器
 * </p>
 *
 * @author Zbais
 * @since 2021-12-07
 */
@Controller
@Api(tags = "UmsMenuController", description = "后台菜单管理")
@RequestMapping("/menu")
public class UmsMenuController {

    @Resource
    private UmsMenuService menuService;

    @ApiOperation(value = "添加后台菜单")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody UmsMenu umsMenu){
        boolean success = menuService.create(umsMenu);
        if(success){
            return CommonResult.success(null);
        }else{
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "修改后台菜单")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Long id,
                               @RequestBody UmsMenu umsMenu){
        boolean success = menuService.update(id, umsMenu);
        if(success){
            return CommonResult.success(null);
        }else{
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "根据ID获取菜单详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<UmsMenu> getItem(@PathVariable Long id){
        UmsMenu umsMenu = menuService.getById(id);
        return CommonResult.success(umsMenu);
    }

    @ApiOperation(value = "根据ID删除后台菜单")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult delete(@PathVariable Long id){
        boolean success = menuService.removeById(id);
        if(success){
            return CommonResult.success(null);
        }else{
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "分页查询后台菜单")
    @RequestMapping(value = "/list/{parentId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<CommonPage<UmsMenu>> list(@PathVariable Long parentId,
                                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
        Page<UmsMenu> menuList = menuService.list(parentId, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(menuList));
    }

    @ApiOperation(value = "树形结构返回所有菜单列表")
    @RequestMapping(value = "/treeList", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<List<UmsMenuNode>> treeList(){
        List<UmsMenuNode> list = menuService.treeList();
        return CommonResult.success(list);
    }

    @ApiOperation(value = "修改菜单显示状态")
    @RequestMapping(value = "/updateHidden/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateHidden(@PathVariable Long id, @RequestParam("hidden")Integer hidden){
        boolean success = menuService.updateHidden(id, hidden);
        if(success){
            return CommonResult.success(null);
        }else{
            return CommonResult.failed();
        }
    }
}

