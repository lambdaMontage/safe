package com.springboot.shiro.dao;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户/角色/权限Dao层
 * Created by shihao 2018/2/24 18:40
 */
public interface UserDao {

    /**
     * 查询用户数量
     *
     * @param jsonObject
     * @return
     */
    int countUser(JSONObject jsonObject);

    /**
     * 查询用户列表
     *
     * @param jsonObject
     * @return
     */
    List<JSONObject> listUser(JSONObject jsonObject);


    /**
     * 查询所有角色
     *
     * @return
     */
    List<JSONObject> getAllRoles();

    /**
     * 效验用户是否存在
     *
     * @param jsonObject
     * @return
     */
    int queryExistUsername(JSONObject jsonObject);

    /**
     * 角色列表
     *
     * @return
     */
    List<JSONObject> listRole();

    /**
     * 新增用户
     *
     * @param jsonObject
     * @return
     */
    int addUser(JSONObject jsonObject);

    /**
     * 修改用户
     *
     * @param jsonObject
     * @return
     */
    int updateUser(JSONObject jsonObject);

    /**
     * 查询所有权限
     *
     * @return
     */
    List<JSONObject> listAllPermission();

    /**
     * 新增角色
     *
     * @param jsonObject
     * @return
     */
    int insertRole(JSONObject jsonObject);

    /**
     * 批量插入角色权限
     *
     * @param roleId      角色ID
     * @param permissions 权限
     * @return
     */
    int insertRolePermission(@Param("roleId") String roleId, @Param("permissions") List<Integer> permissions);

    /**
     * 将角色曾经拥有而修改为不在拥有的权限 delete_status改为2
     *
     * @param roleId      角色ID
     * @param permissions 权限
     * @return
     */
    int removeOldPermission(@Param("roleId") String roleId, @Param("permissions") List<Integer> permissions);

    /**
     * 修改角色名称
     *
     * @param jsonObject
     * @return
     */
    int updateRoleName(JSONObject jsonObject);

    /**
     * 查询某角色的全部数据
     * 在删除和修改角色时调用
     *
     * @param jsonObject
     * @return
     */
    JSONObject getRoleAllInfo(JSONObject jsonObject);

    /**
     * 删除角色
     *
     * @param jsonObject
     * @return
     */
    int removeRole(JSONObject jsonObject);

    /**
     * 删除本角色全部权限
     *
     * @param jsonObject
     * @return
     */
    int removeRoleAllPermission(JSONObject jsonObject);
}
