package net.company.hookahstore.controller;

import net.company.hookahstore.Constants;
import net.company.hookahstore.DTO.UpdateProductFromShoppingCartDTO;
import net.company.hookahstore.form.ProductForm;
import net.company.hookahstore.model.ShoppingCart;
import net.company.hookahstore.service.OrderService;
import net.company.hookahstore.utils.SessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class OrderController {

    private static Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    OrderService orderService;

    @PostMapping(value = "/ajax/json/product/add")
    @ResponseBody
    public UpdateProductFromShoppingCartDTO addProductToShoppingCart(@ModelAttribute("productForm") ProductForm productForm, HttpServletRequest req, HttpServletResponse resp) {
        ShoppingCart shoppingCart = SessionUtils.getCurrentShoppingCart(req);
        orderService.addProductToShoppingCart(productForm, shoppingCart);
        String cookie = orderService.serializeShoppingCart(shoppingCart);
        SessionUtils.updateCurrentShoppingCartCookie(cookie, resp);
        LOGGER.info("Product id:{} , count:{} has been added to Shopping Cart",
                productForm.getIdProduct(), productForm.getCount());
        return new UpdateProductFromShoppingCartDTO(shoppingCart);
    }

    @PostMapping(value = "/ajax/json/shopping-cart/update")
    @ResponseBody
    public UpdateProductFromShoppingCartDTO updateShoppingCart(@ModelAttribute("productForm") ProductForm productForm, HttpServletRequest req, HttpServletResponse resp) {
        ShoppingCart shoppingCart = SessionUtils.getCurrentShoppingCart(req);
        orderService.updateShoppingCart(productForm, shoppingCart);
        String cookie = orderService.serializeShoppingCart(shoppingCart);
        SessionUtils.updateCurrentShoppingCartCookie(cookie, resp);
        LOGGER.info("Product id:{} , count:{} has been changed to Shopping Cart",
                productForm.getIdProduct(), productForm.getCount());
        return new UpdateProductFromShoppingCartDTO(shoppingCart);
    }

    @PostMapping(value = "/ajax/json/product/delete")
    @ResponseBody
    public UpdateProductFromShoppingCartDTO deleteProductFromShoppingCart(@ModelAttribute("productForm") ProductForm productForm, HttpServletRequest req, HttpServletResponse resp) {
        ShoppingCart shoppingCart = SessionUtils.getCurrentShoppingCart(req);
        orderService.removeProductFromShoppingCart(productForm, shoppingCart);
        String cookie = orderService.serializeShoppingCart(shoppingCart);
        SessionUtils.updateCurrentShoppingCartCookie(cookie, resp);
        LOGGER.info("Product id:{} , count:{} has been deleted to Shopping Cart",
                productForm.getIdProduct(), productForm.getCount());
        return new UpdateProductFromShoppingCartDTO(shoppingCart);
    }

    @GetMapping(value = "/shopping-cart")
    public ModelAndView showShoppingCart(HttpServletRequest req) {
        ModelAndView model = new ModelAndView("page-template");
        model.addObject("currentPage", "/WEB-INF/JSP/page/shopping-cart.jsp");
        model.addObject(Constants.CURRENT_SHOPPING_CART, SessionUtils.getCurrentShoppingCart(req));
        return model;
    }
}
