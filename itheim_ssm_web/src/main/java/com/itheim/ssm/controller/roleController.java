package com.itheim.ssm.controller;

import com.itheim.Role;
import com.itheim.dao.roleDao;
import com.itheim.service.roleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * <h3>heima_ssm</h3>
 * <p>角色控制器</p>
 *
 * @author : Dell
 * @date : 2020-08-25 21:58
 **/
@Controller
@RequestMapping("/role")
public class roleController {
    @Autowired
    private roleService service;

    @RequestMapping("/findAll.do")
    @RolesAllowed({"ROLE_USER","ROLE_ROOT"}) //只有ROOT管理员可以访问findAll
    public ModelAndView findAll(){
        ModelAndView mv=new ModelAndView();
        List<Role> list = service.findAll();
        mv.addObject("roleList",list);
        mv.setViewName("role-list");
        return mv;
    }

    @RequestMapping("/save.do")
    public String saveRole(Role role){
        service.saveRole(role);
        return "redirect:findAll.do";

    }
}
