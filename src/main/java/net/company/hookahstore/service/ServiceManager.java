package net.company.hookahstore.service;

import net.company.hookahstore.service.impl.OrderServiceImpl;
import net.company.hookahstore.service.impl.ProductServiceImpl;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

public class ServiceManager {
   private static final Logger LOGGER = LoggerFactory.getLogger(ServiceManager.class);
    private final BasicDataSource dataSource;
    private final ProductService productService;
    private final OrderService orderService;
    private final Properties applicationProperties = new Properties();

    public static ServiceManager getInstance(ServletContext context) {
        ServiceManager instance = (ServiceManager) context.getAttribute("SERVICE_MANAGER");
        if (instance == null) {
            instance = new ServiceManager();
            context.setAttribute("SERVICE_MANAGER", instance);
        }
        return instance;
    }

    private ServiceManager() {
        loadApplicationProperties();
        dataSource = createDataSource();
        productService = new ProductServiceImpl(dataSource);
        orderService = new OrderServiceImpl(dataSource);
    }

    private String getApplicationProperty(String key) {
        return applicationProperties.getProperty(key);
    }

    private void loadApplicationProperties() {
        try (InputStream in = ServiceManager.class.getClassLoader().getResourceAsStream("application.properties")) {
            applicationProperties.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private BasicDataSource createDataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDefaultAutoCommit(false);
        dataSource.setRollbackOnReturn(true);
        dataSource.setDriverClassName(getApplicationProperty("db.driver"));
        dataSource.setUrl(getApplicationProperty("db.url"));
        dataSource.setUsername(getApplicationProperty("db.username"));
        dataSource.setPassword(getApplicationProperty("db.password"));
        dataSource.setInitialSize(Integer.parseInt(getApplicationProperty("db.pool.initSize")));
        dataSource.setMaxTotal(Integer.parseInt(getApplicationProperty("db.pool.maxSize")));
        return dataSource;
    }
    public void close(){
        try {
            dataSource.close();
        }catch (SQLException e){
            LOGGER.error("Close dataSource failed " +e.getMessage(),e);
        }
    }
    public ProductService getProductService() {
        return productService;
    }
    public OrderService getOrderService(){
        return orderService;
    }
}

