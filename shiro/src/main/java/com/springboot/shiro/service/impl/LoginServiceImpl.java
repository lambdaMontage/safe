package com.springboot.shiro.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.springboot.shiro.dao.LoginDao;
import com.springboot.shiro.service.LoginService;
import com.springboot.shiro.service.PermissionService;
import com.springboot.shiro.util.CommonUtil;
import com.springboot.shiro.util.constants.Constants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 登录Service实现层
 * Created by shihao 2018/2/24 18:07
 */

@Service
@Transactional
public class LoginServiceImpl implements LoginService {

    private Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);


    @Autowired
    private LoginDao loginDao;

    @Autowired
    private PermissionService permissionService;

    /**
     * 登录表单的提交
     *
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject authLogin(JSONObject jsonObject) {
        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        Subject subject = SecurityUtils.getSubject();
        JSONObject returnData = new JSONObject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
            returnData.put("result", "success");
        } catch (AuthenticationException a) {
            returnData.put("result", "false");
        }
        return CommonUtil.successJson(returnData);
    }

    /**
     * 根据用户名和密码查询对应的用户
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @Override
    public JSONObject getUser(String username, String password) {
        return loginDao.getUser(username, password);
    }


    /**
     * 查询当前登录用户的权限信息
     *
     * @return
     */
    @Override
    public JSONObject getInfo() {
        //从session中获得信息
        Session session = SecurityUtils.getSubject().getSession();
        JSONObject userInfo = (JSONObject) session.getAttribute(Constants.SESSION_USER_INFO);
        String username = userInfo.getString("username");
        JSONObject returnData = new JSONObject();
        JSONObject userPermission = permissionService.getUserPermission(username);
        //用户权限信息存到session中
        session.setAttribute(Constants.SESSION_USER_PERMISSION, userPermission);
        returnData.put("userPermission", userPermission);
        return CommonUtil.successJson(returnData);
    }


    /**
     * 退出登录
     *
     * @return
     */
    @Override
    public JSONObject logOut() {
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.logout();
        } catch (Exception e) {

        }
        return CommonUtil.successJson();
    }
}
