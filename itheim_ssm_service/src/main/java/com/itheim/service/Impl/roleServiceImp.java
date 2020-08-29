package com.itheim.service.Impl;

import com.itheim.Role;
import com.itheim.dao.roleDao;
import com.itheim.service.roleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <h3>heima_ssm</h3>
 * <p></p>
 *
 * @author : Dell
 * @date : 2020-08-25 22:02
 **/
@Service
@Transactional
public class roleServiceImp  implements roleService {
    @Autowired
    private roleDao roleDao;

    @Override
    public List<Role> findAll() {

        return roleDao.findAll();
    }

    @Override
    public void saveRole(Role role) {
        roleDao.saveRole(role);
    }
}
