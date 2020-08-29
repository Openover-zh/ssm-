package com.itheim.service;

import com.itheim.Permission;
import com.itheim.Role;

import java.util.List;

public interface permissionService {
    public List<Permission> findAll();

    void savePermission(Permission permission);

    List<Role> findUnRole(String id);

    Permission findById(String id);

    void addRoleToUser(String permissionId, String role);
}
