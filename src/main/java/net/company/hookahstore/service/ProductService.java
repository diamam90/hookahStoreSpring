package net.company.hookahstore.service;

import net.company.hookahstore.entity.Category;
import net.company.hookahstore.entity.Producer;
import net.company.hookahstore.entity.Product;
import org.springframework.stereotype.Service;


import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


public interface ProductService {
    List<Product> listAllProduct(int page, int limit);

    int countAllProduct();

    List<Product> listProductByCategory(HttpServletRequest req, int page, int limit);

    int countProductByAside(HttpServletRequest req);

    List<Category> listAllCategories();

    List<Producer> listAllProducers();

    List<Product> listProductBySearch(String category,String searchQuery);

    int countProductBySearch(String category,String searchQuery);

    Map<Category, List<Producer>> mapProducerByCategory();
}
