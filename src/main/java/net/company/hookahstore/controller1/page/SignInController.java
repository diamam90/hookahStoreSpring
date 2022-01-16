package net.company.hookahstore.controller1.page;
/*
import net.company.hookahstore.Constants;
import net.company.hookahstore.controller.AbstractController;
import net.company.hookahstore.utils.RoutingUtils;
import net.company.hookahstore.utils.SessionUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/sign-in")
public class SignInController extends AbstractController {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (SessionUtils.isCurrentAccountCreated(req)){
            RoutingUtils.redirect("/my-orders",req,resp);
        } else {
           // RoutingUtils.redirect(getSocialService().getAuthorizeUrl(), req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       if (SessionUtils.isCurrentAccountCreated(req)){
           RoutingUtils.redirect("/my-orders",req,resp);
       }else {
           String targetUrl = req.getParameter("target");
           if (targetUrl!=null){
               req.getSession().setAttribute(Constants.SUCCESS_REDIRECT_AFTER_SIGNIN,targetUrl);
           }
       //    RoutingUtils.redirect(getSocialService().getAuthorizeUrl(),req,resp);
       }
    }
}
*/