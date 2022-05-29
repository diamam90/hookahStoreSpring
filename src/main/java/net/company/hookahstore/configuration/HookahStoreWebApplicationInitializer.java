package net.company.hookahstore.configuration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.filter.RequestContextFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class HookahStoreWebApplicationInitializer  implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext container) throws ServletException {
        WebApplicationContext ctx = createWebApplicationContext(container);
        registerSpringMVCDispatcherServlet(container,ctx);
        registerFilters(container,ctx);
    }

    private WebApplicationContext createWebApplicationContext(ServletContext container){
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.scan("net.company.hookahstore.configuration");
        ctx.setServletContext(container);
        ctx.refresh();
        return ctx;
    }
    private void registerSpringMVCDispatcherServlet(ServletContext container, WebApplicationContext ctx){
        ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", new DispatcherServlet(ctx));
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");
    }

    private void registerFilters(ServletContext container, WebApplicationContext ctx){
        registerFilter(container,new RequestContextFilter());
        registerFilter(container,new DelegatingFilterProxy("springSecurityFilterChain"),"springSecurityFilterChain");
    }

    private void registerFilter(ServletContext container, Filter filter,String... filterNames){
        String filterName = filterNames.length>0? filterNames[0]: filter.getClass().getSimpleName();
        container.addFilter(filterName,filter).addMappingForUrlPatterns(null,true,"/*");
    }

}
