package net.company.hookahstore.service;

import net.company.hookahstore.entity.Category;
import net.company.hookahstore.entity.Producer;
import net.company.hookahstore.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;


import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


public interface ProductService  {

    Product getProductById(Long id);

    Page<Product> findAll(Pageable pageable);

    List<Category> listAllCategories();

    Page<Product> listProductBySearch(HttpServletRequest req,Pageable pageable);

    Page<Product> listProductByCategoryAndProducer(HttpServletRequest req, Pageable pageable);

    Map<Category,List<Producer>> getProducersByCategoryMap(List<Category> categories);

}
