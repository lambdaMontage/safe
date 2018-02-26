package com.springboot.shiro.service;

import com.alibaba.fastjson.JSONObject;

/**
 * 登录service
 * <p>
 * Created by shihao 2018/2/24 17:48
 */
public interface LoginService {

    /**
     * 登录表单提交
     *
     * @param jsonObject
     * @return
     */
    JSONObject authLogin(JSONObject jsonObject);

    /**
     * 根据用户名和密码查询用户
     *
     * @param username
     * @param password
     * @return
     */
    JSONObject getUser(String username, String password);

    /**
     * 查询当前用户的登录信息
     *
     * @return
     */
    JSONObject getInfo();

    /**
     * 退出登录
     *
     * @return
     */
    JSONObject logOut();
}
