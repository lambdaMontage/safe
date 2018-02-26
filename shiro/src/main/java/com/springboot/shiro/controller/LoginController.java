package com.springboot.shiro.controller;

import com.alibaba.fastjson.JSONObject;
import com.springboot.shiro.service.LoginService;
import com.springboot.shiro.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:登录相关controller
 * @author: shihao
 * @date: Created in 下午9:44 2018/2/25
 */

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * 用户登录
     *
     * @param requestJson
     * @return
     */
    @PostMapping("/auth")
    public JSONObject authLogin(@RequestBody JSONObject requestJson) {
        CommonUtil.hasAllRequired(requestJson, "username,password");
        return loginService.authLogin(requestJson);
    }

    /**
     * 得到用户信息
     *
     * @return
     */
    @PostMapping("/getInfo")
    public JSONObject getInfo() {
        return loginService.getInfo();
    }


    /**
     * 用户退出登录
     *
     * @return
     */
    @PostMapping("/logout")
    public JSONObject logOut() {
        return loginService.logOut();
    }
}
