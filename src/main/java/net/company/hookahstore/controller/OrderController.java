package net.company.hookahstore.controller;

import com.sun.xml.bind.v2.TODO;
import net.company.hookahstore.Constants;
import net.company.hookahstore.DTO.ShoppingCartDTO;
import net.company.hookahstore.entity.Order;
import net.company.hookahstore.form.ProductForm;
import net.company.hookahstore.model.CurrentAccount;
import net.company.hookahstore.model.ShoppingCart;
import net.company.hookahstore.service.NotificationManagerService;
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
    public OrderService orderService;
    @Autowired
    public NotificationManagerService notificationManagerService;

    @PostMapping(value = "/ajax/json/product/add")
    @ResponseBody
    public ShoppingCartDTO addProductToShoppingCart(@ModelAttribute("productForm") ProductForm productForm, HttpServletRequest req, HttpServletResponse resp) {
        ShoppingCart shoppingCart = SessionUtils.getCurrentShoppingCart(req);
        orderService.addProductToShoppingCart(productForm, shoppingCart);
        String cookie = orderService.serializeShoppingCart(shoppingCart);
        SessionUtils.updateCurrentShoppingCartCookie(cookie, resp);
        LOGGER.info("Product id:{} , count:{} has been added to Shopping Cart",
                productForm.getIdProduct(), productForm.getCount());
        return new ShoppingCartDTO(shoppingCart);
    }

    @PostMapping(value = "/ajax/json/shopping-cart/update")
    @ResponseBody
    public ShoppingCartDTO updateShoppingCart(@ModelAttribute("productForm") ProductForm productForm, HttpServletRequest req, HttpServletResponse resp) {
        ShoppingCart shoppingCart = SessionUtils.getCurrentShoppingCart(req);
        orderService.updateShoppingCart(productForm, shoppingCart);
        String cookie = orderService.serializeShoppingCart(shoppingCart);
        SessionUtils.updateCurrentShoppingCartCookie(cookie, resp);
        LOGGER.info("Product id:{} , count:{} has been changed to Shopping Cart",
                productForm.getIdProduct(), productForm.getCount());
        return new ShoppingCartDTO(shoppingCart);
    }

    @PostMapping(value = "/ajax/json/product/delete")
    @ResponseBody
    public ShoppingCartDTO deleteProductFromShoppingCart(@ModelAttribute("productForm") ProductForm productForm, HttpServletRequest req, HttpServletResponse resp) {
        ShoppingCart shoppingCart = SessionUtils.getCurrentShoppingCart(req);
        orderService.removeProductFromShoppingCart(productForm, shoppingCart);
        String cookie = orderService.serializeShoppingCart(shoppingCart);
        SessionUtils.updateCurrentShoppingCartCookie(cookie, resp);
        LOGGER.info("Product id:{} , count:{} has been deleted to Shopping Cart",
                productForm.getIdProduct(), productForm.getCount());
        return new ShoppingCartDTO(shoppingCart);
    }

    @GetMapping("/validateShoppingCart")
    public String test(HttpServletRequest req, HttpServletResponse resp){
        SessionUtils.removeCurrentShoppingCart(req);
        SessionUtils.clearShoppingCartCookie(req,resp);
        //        CurrentAccount testAccount = new CurrentAccount();
//        testAccount.setId(4l);
//        testAccount.setName("Дима");
//        testAccount.setPhone("9312830123");
//        testAccount.setEmail("diamam90@yandex.ru");
//        notificationManagerService.sendPasswordChanged(testAccount);
//        notificationManagerService.sendPasswordChanged((CurrentAccount)session.getAttribute(Constants.CURRENT_ACCOUNT));
        return "page-template";
    }

    @GetMapping(value = "/shopping-cart")
    public ModelAndView showShoppingCart(HttpServletRequest req) {
        ModelAndView model = new ModelAndView("page-template");
        model.addObject("currentPage", "/WEB-INF/JSP/page/shopping-cart.jsp");
        req.getSession().setAttribute(Constants.CURRENT_SHOPPING_CART, SessionUtils.getCurrentShoppingCart(req));
        return model;
    }

    // FIXME cookie doesn't remove;
    @PostMapping(value="/account/makeOrder")
    public String makeOrder(HttpServletRequest req,HttpServletResponse resp){
        if (SessionUtils.isCurrentAccountCreated(req)){
            CurrentAccount currentAccount = SessionUtils.getCurrentAccount(req);
            Long idOrder = orderService.makeOrder(SessionUtils.getCurrentShoppingCart(req),currentAccount);
            SessionUtils.removeCurrentShoppingCart(req);

            LOGGER.info("order {} has been made by account {}",idOrder,currentAccount);
            return "redirect:/order/"+idOrder;
        } else {
            return "redirect:/products";
        }
    }

    @GetMapping("/order/{id}")
    public String showOrder(@PathVariable("id")Long id, Model model,HttpServletRequest req){
//        if (SessionUtils.isCurrentAccountCreated(req)){
//            model.addAttribute("order",orderService.findOrderById(id,SessionUtils.getCurrentAccount(req)));
//            model.addAttribute("currentPage","/WEB-INF/JSP/page/order.jsp");
//            return "page-template";
//        }
//        return "redirect:/error";
        return null;
    }

    @GetMapping("/orders")
    public ModelAndView showMyOrders(HttpServletRequest req){
//        ModelAndView modelAndView = new ModelAndView("page-template");
//        if (!SessionUtils.isCurrentAccountCreated(req)){
//            modelAndView.setViewName("redirect:/products");
//        }  else{
//            modelAndView.addObject("currentPage","page/my-orders.jsp");
//            Page<Order> orders = orderService.listMyOrders(SessionUtils.getCurrentAccount(req), PageRequest.of(getPage(req),Constants.MAX_ORDERS_PER_PAGE));
//            modelAndView.addObject("orderList",orders.getContent());
//            modelAndView.addObject("page",orders);
//        }
//        return modelAndView;
        return null;
    }

    @GetMapping("/ajax/html/orders/more")
    public ModelAndView showMoreOrders(HttpServletRequest req){
        ModelAndView modelAndView = new ModelAndView("fragment/order-list");
        Page<Order> orders = orderService.listMyOrders(SessionUtils.getCurrentAccount(req),PageRequest.of(getPage(req),Constants.MAX_ORDERS_PER_PAGE));
        modelAndView.addObject("orderList",orders.getContent());
        modelAndView.addObject("page",orders);
        return modelAndView;
    }

    private int getPage(HttpServletRequest req){
        return req.getParameter("page")==null?0:Integer.parseInt(req.getParameter("page"));
    }

}
