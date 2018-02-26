package com.springboot.shiro.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.springboot.shiro.dao.UserDao;
import com.springboot.shiro.service.UserService;
import com.springboot.shiro.util.CommonUtil;
import com.springboot.shiro.util.constants.ErrorEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * 用户Service实现层
 * Created by shihao 2018/2/24 18:07
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    /**
     * 查看用户列表
     *
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject listUser(JSONObject jsonObject) {
        CommonUtil.fillPageParam(jsonObject);
        int count = userDao.countUser(jsonObject);
        List<JSONObject> listUser = userDao.listUser(jsonObject);
        return CommonUtil.successPage(jsonObject, listUser, count);
    }

    /**
     * 得到所有角色
     *
     * @return
     */
    @Override
    public JSONObject getAllRoles() {
        List<JSONObject> roles = userDao.getAllRoles();
        return CommonUtil.successPage(roles);
    }


    /**
     * 查看所有角色
     *
     * @return
     */
    @Override
    public JSONObject listRole() {
        List<JSONObject> listRole = userDao.listRole();
        return CommonUtil.successPage(listRole);
    }

    /**
     * 添加用户
     *
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject addUser(JSONObject jsonObject) {
        int exist = userDao.queryExistUsername(jsonObject);
        if (exist > 0) {
            return CommonUtil.errorJson(ErrorEnum.E_10009);
        }
        userDao.addUser(jsonObject);
        return CommonUtil.successJson();
    }


    @Override
    public JSONObject updateUser(JSONObject jsonObject) {
        userDao.updateUser(jsonObject);
        return CommonUtil.successJson();
    }

    @Override
    public JSONObject listAllPermission() {
        List<JSONObject> listAllPermission = userDao.listAllPermission();
        return CommonUtil.successPage(listAllPermission);
    }

    @Override
    public JSONObject addRole(JSONObject jsonObject) {
        userDao.insertRole(jsonObject);
        userDao.insertRolePermission(jsonObject.getString("roleId"), (List<Integer>) jsonObject.get("permission"));
        return CommonUtil.successJson();
    }


    /**
     * 修改角色名称
     *
     * @param jsonObject
     * @return
     */
    @Override
    public JSONObject updateRole(JSONObject jsonObject) {
        String roleId = jsonObject.getString("roleId");
        List<Integer> newPermission = (List<Integer>) jsonObject.get("permission");
        JSONObject roleAllInfo = userDao.getRoleAllInfo(jsonObject);
        Set<Integer> oldPermission = (Set<Integer>) roleAllInfo.get("permissionIds");
        //修改角色名称
        String roleName = jsonObject.getString("roleName");
        if (!roleName.equals(roleAllInfo.getString("roleName"))) {
            userDao.updateRoleName(jsonObject);
        }
        //添加新的权限
        ArrayList<Integer> waitInsert = new ArrayList<>();
        newPermission.stream().forEach(newPerm -> {
            if (!oldPermission.contains(newPerm)) {
                waitInsert.add(newPerm);
            }
        });
        if (waitInsert.size() > 0) {
            userDao.insertRolePermission(roleId, waitInsert);
        }
        //移除旧的不再有的权限
        List<Integer> waitRemove = new ArrayList<>();
        oldPermission.stream().forEach(oldPerm -> {
            if (!newPermission.contains(oldPerm)) {
                waitRemove.add(oldPerm);
            }
        });
        if (waitRemove.size() > 0) {
            userDao.removeOldPermission(roleId, waitRemove);
        }
        return CommonUtil.successJson();
    }

    @Override
    public JSONObject deleteRole(JSONObject jsonObject) {
        JSONObject roleAllInfo = userDao.getRoleAllInfo(jsonObject);
        List<JSONObject> userList = (List<JSONObject>) roleAllInfo.get("users");
        if (userList.size() > 0 && userList != null) {
            return CommonUtil.successJson();
        }
        userDao.removeRole(jsonObject);
        userDao.removeRoleAllPermission(jsonObject);
        return CommonUtil.successJson();
    }
}
