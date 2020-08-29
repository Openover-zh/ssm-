package com.itheim.service;

import com.itheim.Role;

import java.util.List;

public interface roleService {
    public List<Role> findAll();

    void saveRole(Role role);
}
