package net.company.hookahstore.listener;

import net.company.hookahstore.service.ServiceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationListener implements ServletContextListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationListener.class);
    private ServiceManager serviceManager;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            serviceManager = ServiceManager.getInstance(sce.getServletContext());
            sce.getServletContext().setAttribute("CATEGORY_LIST", serviceManager.getProductService().listAllCategories());
            sce.getServletContext().setAttribute("PRODUCER_LIST", serviceManager.getProductService().listAllProducers());
            sce.getServletContext().setAttribute("PRODUCER_BY_CATEGORY_MAP", serviceManager.getProductService().mapProducerByCategory());
        } catch (RuntimeException e){
            LOGGER.error("Web application 'hookahstore' init failed",e.getMessage());
            throw e;
        }
        LOGGER.info("Application init");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        serviceManager.close();
        LOGGER.info("Web application 'hookahstore' destroyed");
    }
}
