package net.company.hookahstore.controller;

import net.company.hookahstore.Constants;
import net.company.hookahstore.entity.Product;

import net.company.hookahstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ProductController {

    @Autowired
    private ProductService productservice;

    @RequestMapping(value="/products",method= RequestMethod.GET)
    public ModelAndView showAllProducts(HttpServletRequest req){
        Page<Product> products = productservice.findAll(PageRequest.of(getPage(req), Constants.ITEMS_PER_PAGE, Sort.by("id")));
        return setAttributesForModelAndView(products,"products.jsp");
    }

    @GetMapping("/categories")
    public ModelAndView showAllProductsByChosenCategory(Model model, HttpServletRequest req){
        Page<Product> products= productservice.listProductByCategoryAndProducer(req,PageRequest.of(getPage(req),Constants.ITEMS_PER_PAGE));
        return setAttributesForModelAndView(products,"categories.jsp");
    }
    @GetMapping("/search")
    public ModelAndView showAllProductsBySearch(Model model, HttpServletRequest req){
        Page<Product> products=productservice.listProductBySearch(req,PageRequest.of(getPage(req),Constants.ITEMS_PER_PAGE));
        return setAttributesForModelAndView(products,"search.jsp");
    }

    private ModelAndView setAttributesForModelAndView(Page<Product> products, String currentPage){
        ModelAndView modelAndView = new ModelAndView("page-template");
        modelAndView.addObject("products",products.getContent());
        modelAndView.addObject("page",products);
        modelAndView.addObject("currentPage","/WEB-INF/JSP/page/"+currentPage);
        return modelAndView;
    }

    private int getPage(HttpServletRequest req){
        if (req.getParameter("page")==null){
            return 0;
        }else {
            return Integer.parseInt(req.getParameter("page"));
        }
    }

}
