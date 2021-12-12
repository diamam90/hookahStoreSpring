package net.company.hookahstore.filter;

import net.company.hookahstore.utils.UrlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractFilter implements Filter {
    protected final Logger LOGGER = LoggerFactory.getLogger(getClass());
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    @Override
    public final void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;
        String url = req.getRequestURI();
        if (UrlUtils.isMediaUrl(url)|| UrlUtils.isStaticUrl(url)){
            chain.doFilter(req,resp);
        }else {
            doFilter(req,resp,chain);
        }
    }

    public abstract void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException;
}
