package net.company.hookahstore.controller;


import net.company.hookahstore.entity.Product;

import net.company.hookahstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class ProductController {

    @Autowired
    private ProductService productservice;



    @RequestMapping(value="/products",method= RequestMethod.GET)
    public String showAllProducts(Model model){
        Product p = productservice.getProductById(33490L);
        model.addAttribute("products",p);
        model.addAttribute("currentPage","products.jsp");
        return "page-template";
    }
}
