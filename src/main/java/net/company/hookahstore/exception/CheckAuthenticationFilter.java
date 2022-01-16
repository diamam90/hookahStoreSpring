package net.company.hookahstore.exception;


import net.company.hookahstore.Constants;
import net.company.hookahstore.filter.AbstractFilter;
import net.company.hookahstore.utils.RoutingUtils;
import net.company.hookahstore.utils.SessionUtils;
import net.company.hookahstore.utils.UrlUtils;
import net.company.hookahstore.utils.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "CheckAuthenticationFilter")
public class CheckAuthenticationFilter extends AbstractFilter {

    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        if (SessionUtils.isCurrentAccountCreated(req)){
            chain.doFilter(req,resp);
        }else {
            String requestUrl = WebUtils.getCurrentRequestUrl(req);
            if (UrlUtils.isAjaxUrl(requestUrl)){
                resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                resp.getWriter().println("401");
            } else{
                req.getSession().setAttribute(Constants.SUCCESS_REDIRECT_AFTER_SIGNIN,requestUrl);
                RoutingUtils.redirect("/login",req,resp);
            }
        }
    }
}
