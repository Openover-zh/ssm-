package com.itheim.dao;

import com.itheim.Permission;
import com.itheim.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface permissionDao {
    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{roleId})")
    public List<Permission> findByRoleId(String roleId);


    @Select("select * from permission")
    public List<Permission> findAll();
    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    void savePermission(Permission permission);

    @Select("select * from role where id not in (select roleId from role_permission where permissionId=#{id} )")
    List<Role> findUnRole(String id);


    @Select("select * from permission where id=#{id}")
    Permission findById(String id);

    @Insert("insert into role_permission(permissionId,roleId) values(#{permissionId},#{roleId})")
    void addRoleToUser(@Param("permissionId") String permissionId,@Param("roleId") String role);
}
