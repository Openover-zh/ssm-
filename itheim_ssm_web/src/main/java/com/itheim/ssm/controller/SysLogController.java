package com.itheim.ssm.controller;

import com.itheim.SysLog;
import com.itheim.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * <h3>heima_ssm</h3>
 * <p>查询日志信息</p>
 *
 * @author : Dell
 * @date : 2020-08-27 17:47
 **/
@Controller
@RequestMapping("/sysLog")
public class SysLogController {
    @Autowired
    private SysLogService service;

    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        ModelAndView mv=new ModelAndView();
        List<SysLog> list = service.findAll();
        mv.addObject("sysLogs",list);
        mv.setViewName("syslog-list");
        return mv;
    }

    @RequestMapping("/search.do")
    public ModelAndView search(@RequestParam(name = "search",required = true) String username){
        List<SysLog> sysLogs=service.findByName(username);
        ModelAndView mv=new ModelAndView();
        mv.addObject("sysLogs",sysLogs);
        mv.setViewName("syslog-list");
        return mv;

    }

}
