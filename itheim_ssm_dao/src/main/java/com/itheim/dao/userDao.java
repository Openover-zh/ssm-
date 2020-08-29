package com.itheim.dao;

import com.itheim.Role;
import com.itheim.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface userDao {
    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "email", property = "email"),
            @Result(column = "password", property = "password"),
            @Result(column = "phoneNum", property = "phoneNum"),
            @Result(column = "status", property = "status"),
            @Result(column = "id", property = "roles", javaType = java.util.List.class, many = @Many(select = "com.itheim.dao.roleDao.findRoleByUserId"))
    })
    public UserInfo findByName(String username);


    @Select("select * from users")
    public List<UserInfo> findAll();
    @Insert("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void saveUser(UserInfo userInfo);
    @Select("select * from users where id=#{id}")
    @Results({
            @Result(id=true,column = "id",property = "id"),
            @Result(column = "username",property = "username"),
            @Result(column = "id",property = "roles",many = @Many(select = "com.itheim.dao.roleDao.findRoleByUserId")),
    })
    UserInfo findById(String id);

    @Select("select * from role where id not in (select roleId from users_role where userId=#{userId})")
    List<Role> findUnRole(String userId);

    /**
     * 用户添加角色
     * @param userId
     * @param role
     */
    @Insert("insert into users_role(userId,roleId) values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") String userId,@Param("roleId") String role);
}
