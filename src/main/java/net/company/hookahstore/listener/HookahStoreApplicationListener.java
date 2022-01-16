package net.company.hookahstore.listener;

import net.company.hookahstore.Constants;
import net.company.hookahstore.entity.Category;
import net.company.hookahstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import java.util.List;

@Component
public class HookahStoreApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    ProductService productService;

    @Autowired
    ServletContext context;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        List<Category> categories = productService.listAllCategories();
        context.setAttribute(Constants.CATEGORY_LIST, categories);
        context.setAttribute(Constants.PRODUCER_BY_CATEGORY_MAP, productService.getProducersByCategoryMap(categories));
    }
}
