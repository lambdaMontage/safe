package com.springboot.shiro.controller;

import com.alibaba.fastjson.JSONObject;
import com.springboot.shiro.service.UserService;
import com.springboot.shiro.util.CommonUtil;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 用户相关controller
 * @author: shihao
 * @date: Created in 下午9:44 2018/2/25
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    /**
     * 查询用户列表
     *
     * @param request
     * @return
     */
    @RequiresPermissions("user:list")
    @GetMapping("/list")
    public JSONObject listUser(HttpServletRequest request) {
        return userService.listUser(CommonUtil.request2Json(request));

    }

    /**
     * 查询所有角色 shiro权限路径，Logical.OR 是满足add和update其中一个即可
     *
     * @return
     */
    @RequiresPermissions(value = {"user:add", "user:update"}, logical = Logical.OR)
    @GetMapping("/getAllRoles")
    public JSONObject getAllRoles() {
        return userService.getAllRoles();

    }

    /**
     * 角色列表
     *
     * @return
     */
    @RequiresPermissions("role:list")
    @GetMapping("/listRole")
    public JSONObject listRole() {
        return userService.listRole();
    }

    /**
     * 添加用户
     *
     * @param requestJson
     * @return
     */
    @RequiresPermissions("user:add")
    @PostMapping("/addUser")
    public JSONObject addUser(@RequestBody JSONObject requestJson) {
        CommonUtil.hasAllRequired(requestJson, "username,password,nickname,roleId");
        return userService.addUser(requestJson);
    }


    /**
     * 更新用户
     *
     * @param requestJson
     * @return
     */
    @RequiresPermissions("user:update")
    @PostMapping("/updateUser")
    public JSONObject updateUser(@RequestBody JSONObject requestJson) {
        CommonUtil.hasAllRequired(requestJson, " nickname,roleId,deleteStatus,userId");
        return userService.updateUser(requestJson);
    }


    /**
     * 查询所有权限
     *
     * @return
     */
    @RequiresPermissions("role:list")
    @GetMapping("/listAllPermission")
    public JSONObject listAllPermission() {
        return userService.listAllPermission();
    }


    /**
     * 添加角色
     *
     * @param jsonObject
     * @return
     */
    @RequiresPermissions("role:add")
    @PostMapping("/addRole")
    public JSONObject addRole(@RequestBody JSONObject jsonObject) {
        CommonUtil.hasAllRequired(jsonObject, "roleName,permissions");
        return userService.addRole(jsonObject);
    }

    /**
     * 更新角色
     *
     * @param jsonObject
     * @return
     */
    @RequiresPermissions("role:update")
    @PostMapping("/updateRole")
    public JSONObject updateRole(@RequestBody JSONObject jsonObject) {
        CommonUtil.hasAllRequired(jsonObject, "roleId,roleName,permissions");
        return userService.updateRole(jsonObject);
    }


    /**
     * 删除角色
     *
     * @param jsonObject
     * @return
     */
    @RequiresPermissions("role:delete")
    @PostMapping("/deleteRole")
    public JSONObject deleteRole(@RequestBody JSONObject jsonObject) {
        CommonUtil.hasAllRequired(jsonObject, "roleId");
        return userService.deleteRole(jsonObject);
    }
}
