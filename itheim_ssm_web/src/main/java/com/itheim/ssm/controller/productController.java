package com.itheim.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.itheim.Orders;
import com.itheim.Product;
import com.itheim.service.productService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * <h3>heima_ssm</h3>
 * <p></p>
 *
 * @author : Dell
 * @date : 2020-08-14 15:15
 **/
@Controller
@RequestMapping("/product")
public class productController {
    @Autowired
    private productService service;

    /**
     * 查询所有
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Product> list = service.findAll();
//        System.out.println(all);
        mv.addObject("productList",list);
        mv.setViewName("product-list");
        return  mv;
    }

    @RequestMapping("/findAllByPage.do")
    public ModelAndView findAllByPage(@RequestParam(name = "page",required =true,defaultValue = "1") Integer page,
                                      @RequestParam(name = "pageSize",required = false,defaultValue = "4")Integer pageSize)
    {
        List<Product> list = service.findAllByPages(page, pageSize);
        PageInfo pageInfo=new PageInfo(list);
        ModelAndView model=new ModelAndView();
        model.setViewName("product-list");
        model.addObject("pageInfo",pageInfo);
        return model;

    }


    @RequestMapping("/save.do")
    public String save(Product product){
        service.saveProduct(product);
        return "redirect:findAll.do";
    }


}
