package com.springboot.shiro.service;

import com.alibaba.fastjson.JSONObject;

/**
 * 权限Service层
 * Created by shihao 2018/2/24 18:11
 */
public interface PermissionService {

    /**
     * 查询某用户的 角色  菜单列表   权限列表
     *
     * @param username
     * @return
     */
    JSONObject getUserPermission(String username);
}
