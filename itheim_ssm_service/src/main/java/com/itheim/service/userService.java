package com.itheim.service;

import com.itheim.Role;
import com.itheim.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface userService  extends UserDetailsService {
    public List<UserInfo> findAll();

    void saveUser(UserInfo userInfo);

    UserInfo findById(String id);

    List<Role> findUnRole(String id);

    void addRoleToUser(String userId, String role);
}
