package com.itheim.service.Impl;

import com.itheim.Permission;
import com.itheim.Role;
import com.itheim.dao.permissionDao;
import com.itheim.service.permissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <h3>heima_ssm</h3>
 * <p></p>
 *
 * @author : Dell
 * @date : 2020-08-25 23:09
 **/
@Service
@Transactional
public class permissionServiceImp implements permissionService {
    @Autowired
    private permissionDao dao;
    @Override
    public List<Permission> findAll() {
        return dao.findAll();
    }

    @Override
    public void savePermission(Permission permission) {
        dao.savePermission(permission);
    }

    @Override
    public List<Role> findUnRole(String id) {
        return dao.findUnRole(id);
    }

    @Override
    public Permission findById(String id) {
        return dao.findById(id);
    }

    @Override
    public void addRoleToUser(String permissionId, String role) {
        dao.addRoleToUser(permissionId,role);
    }
}
