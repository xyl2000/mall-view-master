package com.zbais.mall.modules.ums.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zbais.mall.modules.ums.dto.UmsAdminParam;
import com.zbais.mall.modules.ums.dto.UpdateAdminPasswordParam;
import com.zbais.mall.modules.ums.model.UmsAdmin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zbais.mall.modules.ums.model.UmsResource;
import com.zbais.mall.modules.ums.model.UmsRole;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Zbais
 * @since 2021-12-07
 * 后台管理员管理
 */
@Service
public interface UmsAdminService extends IService<UmsAdmin> {
    /**
     * 根据用户名获取后台管理员
     * @param username 用户名
     * @return UmsAdmin
     */
    UmsAdmin getAdminByUsername(String username);

    /**
     * 注册功能
     * @param umsAdminParam 管理员参数
     * @return UmsAdmin
     */
    UmsAdmin register(UmsAdminParam umsAdminParam);

    /**
     * 登录功能
     * @param username 用户名
     * @param password 密码
     * @return 生成JWT的Token
     */
    String login(String username, String password);

    /**
     * 刷新token的功能
     * @param oldToken 旧的token
     * @return String
     */
    String refreshToken(String oldToken);

    /**
     * 根据用户名或昵称分页查询用户
     * @param keyword 关键字
     * @param pageSize 页面大小
     * @param pageNum 页码
     * @return 管理员页
     */
    Page<UmsAdmin> list(String keyword, Integer pageSize, Integer pageNum);

    /**
     * 修改指定用户信息
     * @param id id
     * @param admin 管理员
     * @return 布尔值
     */
    boolean update(Long id, UmsAdmin admin);

    /**
     * 删除指定用户
     * @param id id
     * @return 布尔值
     */
    boolean delete(Long id);

    /**
     * 修改用户角色关系
     * @param adminId 管理员id
     * @param roleIds 角色id
     * @return 整型
     */
    @Transactional
    int updateRole(Long adminId, List<Long> roleIds);

    /**
     * 获取用户角色集
     * @param adminId 管理员id
     * @return 角色列表
     */
    List<UmsRole> getRoleList(Long adminId);

    /**
     * 获取指定用户的可访问资源
     * @param adminId 管理员id
     * @return 资源列表
     */
    List<UmsResource> getResourceList(Long adminId);

    /**
     * 修改密码
     * @param updateAdminPasswordParam 密码参数
     * @return 整型
     */
    int updatePassword(UpdateAdminPasswordParam updateAdminPasswordParam);

    /**
     * 获取用户信息
     * @param username 用户名
     * @return 细节
     */
    UserDetails loadUserByUsername(String username);
}
