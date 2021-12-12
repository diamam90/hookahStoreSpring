package net.company.hookahstore.filter;

import net.company.hookahstore.exception.AbstractApplicationException;
import net.company.hookahstore.exception.InternalServerErrorException;
import net.company.hookahstore.exception.ResourceNotFoundException;
import net.company.hookahstore.exception.ValidationException;
import net.company.hookahstore.utils.RoutingUtils;
import net.company.hookahstore.utils.UrlUtils;
import org.json.JSONObject;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.nio.file.AccessDeniedException;

@WebFilter(filterName="ErrorHandlerFilter")
public class ErrorHandlerFilter extends AbstractFilter{
    private static final String INTERNAL_ERROR = "Internal error";

    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        try{
            chain.doFilter(req,new ThrowExceptionInsteadOnSendErrorResponse(resp));
        } catch (Throwable th){
            String requestUrl = req.getRequestURI();
            if (th instanceof ValidationException){
                LOGGER.warn("Request is not valid" + th.getMessage(),th);
            } else {
                LOGGER.error("Request " +requestUrl+" failed: " + th.getMessage(),th );
            }
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            handleException(requestUrl,th,req,resp);
        }
    }
    private int getStatusCode(Throwable th){
        if (th instanceof AbstractApplicationException){
            return ((AbstractApplicationException) th).getCode();
        }else {
            return (HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
    private void handleException(String requestUrl,Throwable th, HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
        int statusCode = getStatusCode(th);
        resp.setStatus(statusCode);

        if (UrlUtils.isAjaxJsonUrl(requestUrl)){
            JSONObject json = new JSONObject();
            json.put("message", th instanceof ValidationException ? th.getMessage():INTERNAL_ERROR);
            RoutingUtils.sendJSON(json,req,resp);
        }else if (UrlUtils.isAjaxHtmlUrl(requestUrl)){
            RoutingUtils.sendHTMLFragment(INTERNAL_ERROR,req,resp);
        } else {
            req.setAttribute("statusCode",statusCode);
            RoutingUtils.forwardToPage("error.jsp",req,resp);
        }
    }

    private static class ThrowExceptionInsteadOnSendErrorResponse extends HttpServletResponseWrapper {
        public ThrowExceptionInsteadOnSendErrorResponse(HttpServletResponse response){
            super(response);
        }

        @Override
        public void sendError(int sc, String msg) throws IOException {
            switch (sc){
                case 403:{
                    throw new AccessDeniedException(msg);
                }
                case 404: {
                    throw new ResourceNotFoundException(msg);
                }
                case 400:{
                    throw new ValidationException(msg);
                }
                default: {
                    throw new InternalServerErrorException(msg);
                }
            }
        }

        @Override
        public void sendError(int sc) throws IOException {
            super.sendError(sc,INTERNAL_ERROR);
        }
    }
}
