package net.company.hookahstore.controller;

import net.company.hookahstore.Constants;
import net.company.hookahstore.DTO.UpdateProductFromShoppingCartDTO;
import net.company.hookahstore.entity.Order;
import net.company.hookahstore.form.ProductForm;
import net.company.hookahstore.model.CurrentAccount;
import net.company.hookahstore.model.ShoppingCart;
import net.company.hookahstore.service.OrderService;
import net.company.hookahstore.utils.SessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
        req.getSession().setAttribute(Constants.CURRENT_SHOPPING_CART, SessionUtils.getCurrentShoppingCart(req));
        return model;
    }

    @PostMapping(value="/account/makeOrder")
    public String makeOrder(HttpServletRequest req){
        if (SessionUtils.isCurrentAccountCreated(req)){
            CurrentAccount currentAccount = SessionUtils.getCurrentAccount(req);
            Long idOrder = orderService.makeOrder(SessionUtils.getCurrentShoppingCart(req),currentAccount);
            SessionUtils.clearCurrentShoppingCart(req);
            LOGGER.info("order {} has been made by account {}",idOrder,currentAccount);
            return "redirect:/order/"+idOrder;
        } else {
            return "redirect:/account/login";
        }
    }

    @GetMapping("/order/{id}")
    public String showOrder(@PathVariable("id")Long id, Model model,HttpServletRequest req){
        if (SessionUtils.isCurrentAccountCreated(req)){
            model.addAttribute("order",orderService.findOrderById(id,SessionUtils.getCurrentAccount(req)));
            model.addAttribute("currentPage","/WEB-INF/JSP/page/order.jsp");
            return "page-template";
        }
        return "redirect:/error";
    }

    @GetMapping("/orders")
    public ModelAndView showMyOrders(HttpServletRequest req){
        ModelAndView modelAndView = new ModelAndView("page-template");
        modelAndView.addObject("currentPage","/WEB-INF/JSP/fragment/order-list.jsp");
        Page<Order> orders = orderService.listMyOrders(SessionUtils.getCurrentAccount(req), PageRequest.of(getPage(req),Constants.MAX_ORDERS_PER_PAGE));
        modelAndView.addObject("orderList",orders.getContent());
        modelAndView.addObject("page",orders);
        return modelAndView;
    }

    private int getPage(HttpServletRequest req){
        return req.getParameter("page")==null?0:Integer.parseInt(req.getParameter("page"));
    }

}
