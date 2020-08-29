package com.itheim.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.itheim.Orders;
import com.itheim.service.ordersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * <h3>heima_ssm</h3>
 * <p>查询用户订单</p>
 *
 * @author : Dell
 * @date : 2020-08-20 17:42
 **/
@Controller
@RequestMapping("/orders")
public class ordersController {
    @Autowired
    private ordersService service;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(){
        List<Orders> list = service.findAll();
        ModelAndView model=new ModelAndView();
        model.addObject("ordersList",list);
        model.setViewName("orders-list");
        return model;


    }

    /**
     * 分页查询
     * @param page
     * @param pageSize
     * @return
     */
    @RequestMapping("/findAllByPage.do")
    public ModelAndView findAllByPage(@RequestParam(name = "page",required =true,defaultValue = "1") Integer page,
                                      @RequestParam(name = "pageSize",required = false,defaultValue = "4")Integer pageSize)
    {
        List<Orders> list = service.findAllByPages(page, pageSize);
        PageInfo pageInfo=new PageInfo(list);
        ModelAndView model=new ModelAndView();
        model.setViewName("orders-list");
        model.addObject("pageInfo",pageInfo);
        return model;

    }
    @RequestMapping("/findById.do")
    public ModelAndView findById(String id) throws Exception {
        ModelAndView model=new ModelAndView();
        Orders orders = service.findById(id);
        model.addObject("orders",orders);
        model.setViewName("orders-show");


        return  model;
    }
}
