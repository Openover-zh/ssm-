package com.itheim.ssm.controller;

import com.itheim.Role;
import com.itheim.UserInfo;
import com.itheim.service.userService;
import org.apache.ibatis.annotations.Param;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * <h3>heima_ssm</h3>
 * <p>用户查询</p>
 *
 * @author : Dell
 * @date : 2020-08-25 19:43
 **/
@Controller
@RequestMapping("/user")
public class userController {
    @Autowired
    private userService service;


    @RequestMapping("/findAll.do")
    @Secured({"ROLE_USER","ROLE_ROOT"})
    public ModelAndView findAll(){
        List<UserInfo> list = service.findAll();
        ModelAndView mv=new ModelAndView();
        mv.addObject("userList",list);
        mv.setViewName("user-list");
        return mv;
    }

    @RequestMapping("/save.do")
    public String saveUser(UserInfo userInfo){
        service.saveUser(userInfo);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(@Param("id") String id){
        UserInfo userInfo=service.findById(id);
        ModelAndView mv=new ModelAndView();
        mv.addObject("user",userInfo);
        mv.setViewName("user-show");
        return mv;
    }

    @RequestMapping("findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true) String id){
        ModelAndView mv = new ModelAndView();
        List<Role> roles=service.findUnRole(id);
        UserInfo userInfo = service.findById(id);

        mv.addObject("roleList",roles);
        mv.addObject("user",userInfo);
        mv.setViewName("user-role-add");

        return mv;
    }


    @RequestMapping("addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "ids",required = true) String[] roles,@RequestParam(name ="userId",required = true) String userId){
        for (String role : roles) {
            service.addRoleToUser(userId,role);
        }

            return "redirect:findAll.do";
    }
}
