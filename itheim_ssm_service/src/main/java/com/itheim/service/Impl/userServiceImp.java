package com.itheim.service.Impl;

import com.itheim.Role;
import com.itheim.UserInfo;
import com.itheim.dao.userDao;
import com.itheim.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <h3>heima_ssm</h3>
 * <p></p>
 *
 * @author : Dell
 * @date : 2020-08-25 09:52
 **/
@Service("userService")
public class userServiceImp implements userService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private userDao dao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = dao.findByName(username);
        List<Role> roles = userInfo.getRoles();
        System.out.println(roles);
        //没有加密时添加 "{noop}" 密码前
        User user=new User(userInfo.getUsername(),userInfo.getPassword(),userInfo.getStatus()==1?true:false,
                true,true,true,getAuthority(roles));

        return user;
    }

    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles){
        List<SimpleGrantedAuthority> list=new ArrayList<>();
        System.out.println(roles);
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
//        list.add(new SimpleGrantedAuthority("ROLE_USER"));
        return list;
    }

    @Override
    public List<UserInfo> findAll() {
        return dao.findAll();
    }

    @Override
    public void saveUser(UserInfo userInfo) {
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
        dao.saveUser(userInfo);
    }

    @Override
    public UserInfo findById(String id) {
        return dao.findById(id);
    }

    @Override
    public List<Role> findUnRole(String userId) {
        return dao.findUnRole(userId);
    }

    @Override
    public void addRoleToUser(String userId, String role) {
        dao.addRoleToUser(userId,role);
    }


}
