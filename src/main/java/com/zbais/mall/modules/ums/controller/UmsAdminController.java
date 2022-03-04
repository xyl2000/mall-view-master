package com.zbais.mall.modules.ums.controller;


import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zbais.mall.common.api.CommonPage;
import com.zbais.mall.common.api.CommonResult;
import com.zbais.mall.modules.ums.dto.UmsAdminLoginParam;
import com.zbais.mall.modules.ums.dto.UmsAdminParam;
import com.zbais.mall.modules.ums.dto.UpdateAdminPasswordParam;
import com.zbais.mall.modules.ums.model.UmsAdmin;
import com.zbais.mall.modules.ums.model.UmsResource;
import com.zbais.mall.modules.ums.model.UmsRole;
import com.zbais.mall.modules.ums.service.UmsAdminService;
import com.zbais.mall.modules.ums.service.UmsRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 后台用户表 前端控制器
 * </p>
 *
 * @author Zbais
 * @since 2021-12-07
 */
@RestController
@RequestMapping("/admin")
@Api(tags = "UmsAdminController", description = "后台用户管理")
public class UmsAdminController {
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Resource
    private UmsAdminService adminService;
    @Autowired
    private UmsRoleService roleService;

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public CommonResult<UmsAdmin> register(@Validated @RequestBody UmsAdminParam umsAdminParam){
        UmsAdmin umsAdmin = adminService.register(umsAdminParam);
        if(umsAdmin == null){
            return CommonResult.failed();
        }
        return CommonResult.success(umsAdmin);
    }

    @ApiOperation(value = "登录以后返回token")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@Validated @RequestBody UmsAdminLoginParam umsAdminLoginParam){
        String token = adminService.login(umsAdminLoginParam.getUsername(), umsAdminLoginParam.getUsername());
        if(token == null){
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHeader);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation(value = "刷新token")
    @RequestMapping(value = "/refreshToken", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult refreshToken(HttpServletRequest request){
        String token = request.getHeader(tokenHeader);
        String refreshToken = adminService.refreshToken(token);
        if(refreshToken == null){
            return CommonResult.failed("token已经过期了");
        }
        Map<String,String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHeader", tokenHeader);
        return  CommonResult.success(tokenMap);
    }

    @ApiOperation(value = "获取当前登录用户信息")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getAdminInfo(Principal principal){
        if(principal == null){
            return CommonResult.unauthorized(null);
        }
        String username = principal.getName();
        UmsAdmin umsAdmin = adminService.getAdminByUsername(username);
        Map<String, Object> data = new HashMap<>();
        data.put("username", umsAdmin.getUsername());
        data.put("menus", roleService.getMenuList(umsAdmin.getId()));
        data.put("icon", umsAdmin.getIcon());
        List<UmsRole> roleList = adminService.getRoleList(umsAdmin.getId());
        if(CollUtil.isNotEmpty(roleList)){
            List<String> roles = roleList.stream().map(UmsRole::getName).collect(Collectors.toList());
            data.put("roles", roles);
        }
        return CommonResult.success(data);
    }

    @ApiOperation(value = "登出功能")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult logout(){
        return CommonResult.success(null);
    }


    public CommonResult<CommonPage<UmsAdmin>> list(@RequestParam(value = "keyword", required = false) String keyword,
                                                   @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum){
        Page<UmsAdmin> adminList = adminService.list(keyword, pageSize, pageNum);
        return CommonResult.success(CommonPage.restPage(adminList));
    }

    @ApiOperation(value = "获取指定用户信息")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<UmsAdmin> getItem(@PathVariable Long id){
        UmsAdmin admin = adminService.getById(id);
        return CommonResult.success(admin);
    }

    @ApiOperation(value = "修改指定用户信息")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult update(@PathVariable Long id, @RequestBody UmsAdmin admin){
        boolean success = adminService.update(id, admin);
        if(success){
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "修改指定用户密码")
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    public CommonResult updatePassword(@Validated @RequestBody UpdateAdminPasswordParam updatePasswordParam){
        int status = adminService.updatePassword(updatePasswordParam);
        if(status > 0){
            return CommonResult.success(status);
        }else if(status == -1){
            return CommonResult.failed("提交参数不合法");
        }else if(status == -2){
            return CommonResult.failed("找不到该用户");
        }else if(status == -3){
            return CommonResult.failed("旧密码错误");
        }else{
            return CommonResult.failed();
        }
    }


    @ApiOperation(value = "删除指定用户信息")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@PathVariable Long id){
        boolean success = adminService.delete(id);
        if(success){
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "修改账号状态")
    @RequestMapping(value = "/updateStatus/{id}")
    @ResponseBody
    public CommonResult updateStatus(@PathVariable Long id, @RequestParam(value = "status") Integer status){
        UmsAdmin umsAdmin = new UmsAdmin();
        umsAdmin.setStatus(status);
        boolean success = adminService.update(id, umsAdmin);
        if(success){
            return CommonResult.success(null);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "给用户分配角色")
    @RequestMapping(value = "/role/update", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updateRole(@RequestParam("adminId") Long adminId,
                                   @RequestParam("roleIds") List<Long> roleIds){
        int count = adminService.updateRole(adminId, roleIds);
        if(count >= 0){
            return CommonResult.success(count);
        }
        return CommonResult.failed();
    }

    @ApiOperation(value = "获取指定用户的角色")
    @RequestMapping(value = "/role/{adminId}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<UmsRole>> getRoleList(@PathVariable Long adminId){
        List<UmsRole> roleList = adminService.getRoleList(adminId);
        return CommonResult.success(roleList);
    }


}

