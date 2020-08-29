package com.itheim.dao;

import com.itheim.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface roleDao {
    @Select("select * from role where id in (select roleId from users_role where userId=#{userId})")
    @Results({
            @Result(id=true,column ="id",property = "id"),
            @Result(column = "roleName",property = "roleName"),
            @Result(column = "roleDesc",property = "roleDesc"),
            @Result(column = "id",property = "permissions",javaType =List.class,many = @Many(select="com.itheim.dao.permissionDao.findByRoleId")),
    })
    public List<Role> findRoleByUserId(String userId);

    @Select("select * from role")
    public List<Role> findAll();
    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void saveRole(Role role);
}
