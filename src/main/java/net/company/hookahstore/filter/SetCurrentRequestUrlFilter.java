package net.company.hookahstore.filter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName="SetCurrentRequestUrlFilter")
public class SetCurrentRequestUrlFilter extends AbstractFilter{
    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        req.setAttribute("url",req.getHeader("referer"));
        chain.doFilter(req,resp);
    }
}
