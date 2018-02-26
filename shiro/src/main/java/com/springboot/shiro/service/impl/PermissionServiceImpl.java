package com.springboot.shiro.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.springboot.shiro.dao.PermissionDao;
import com.springboot.shiro.service.PermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * 权限Service实现层
 * Created by shihao 2018/2/24 18:07
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class PermissionServiceImpl implements PermissionService {

    private Logger logger = LoggerFactory.getLogger(PermissionServiceImpl.class);

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public JSONObject getUserPermission(String username) {
        JSONObject userPermission = permissionDao.getUserPermission(username);
        int adminRoleId = 1;
        String roleIdKey = "roleId";
        if (adminRoleId == userPermission.getIntValue(roleIdKey)) {
            Set<String> allMenu = permissionDao.getAllMenu();
            Set<String> allPermission = permissionDao.getAllPermission();
            userPermission.put("allMenu", allMenu);
            userPermission.put("allPermission", allPermission);
        }
        return userPermission;
    }
}
