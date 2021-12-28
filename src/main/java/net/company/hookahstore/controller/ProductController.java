package net.company.hookahstore.controller;

import net.company.hookahstore.Constants;
import net.company.hookahstore.entity.Product;

import net.company.hookahstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.sql.DataSource;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private ProductService productservice;



    @RequestMapping(value="/products",method= RequestMethod.GET)
    public String showAllProducts(Model model){
        List<Product> products = productservice.listAllProduct(1,Constants.ITEMS_PER_PAGE);
        model.addAttribute("products",products);
        return "products";
    }
}
