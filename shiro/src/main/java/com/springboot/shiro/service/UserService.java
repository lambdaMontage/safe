package com.springboot.shiro.service;

import com.alibaba.fastjson.JSONObject;

/**
 * 用户Service层
 * <p>
 * Created by shihao 2018/2/24 18:11
 */
public interface UserService {

    /**
     * 查询所有用户
     *
     * @param jsonObject
     * @return
     */
    JSONObject listUser(JSONObject jsonObject);

    /**
     * 查询所有角色
     *
     * @return
     */
    JSONObject getAllRoles();

    /**
     * 角色列表
     *
     * @return
     */
    JSONObject listRole();

    /**
     * 添加用户
     *
     * @param jsonObject
     * @return
     */
    JSONObject addUser(JSONObject jsonObject);

    /**
     * 修改用户
     *
     * @param jsonObject
     * @return
     */
    JSONObject updateUser(JSONObject jsonObject);

    /**
     * 查询所有权限
     *
     * @return
     */
    JSONObject listAllPermission();

    /**
     * 添加角色
     *
     * @param jsonObject
     * @return
     */
    JSONObject addRole(JSONObject jsonObject);

    /**
     * 修改角色
     *
     * @param jsonObject
     * @return
     */
    JSONObject updateRole(JSONObject jsonObject);

    /**
     * 删除角色
     *
     * @param jsonObject
     * @return
     */
    JSONObject deleteRole(JSONObject jsonObject);
}
