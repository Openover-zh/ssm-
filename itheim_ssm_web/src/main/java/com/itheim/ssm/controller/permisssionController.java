package com.itheim.ssm.controller;

import com.itheim.Permission;
import com.itheim.Role;
import com.itheim.UserInfo;
import com.itheim.service.permissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * <h3>heima_ssm</h3>
 * <p>资源权限管理</p>
 *
 * @author : Dell
 * @date : 2020-08-25 23:08
 **/
@Controller
@RequestMapping("/permission")
public class permisssionController {
    @Autowired
    private permissionService service;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        ModelAndView mv=new ModelAndView();
        List<Permission> list = service.findAll();
        mv.addObject("permissionList",list);
        mv.setViewName("permission-list");
        return mv;
    }


    @RequestMapping("save.do")
    public String savePermission(Permission permission){
        System.out.println(permission);
        service.savePermission(permission);
        return "redirect:findAll.do";
    }


    @RequestMapping("findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true) String id){
        ModelAndView mv = new ModelAndView();
        List<Role> roles=service.findUnRole(id);
        Permission permission = service.findById(id);

        mv.addObject("roleList",roles);
        mv.addObject("permission",permission);
        mv.setViewName("permission-role-add");

        return mv;
    }


    @RequestMapping("addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "ids",required = true) String[] roles,@RequestParam(name ="permissionId",required = true) String permissionId){
        for (String role : roles) {
            service.addRoleToUser(permissionId,role);
        }

        return "redirect:findAll.do";
    }
}
