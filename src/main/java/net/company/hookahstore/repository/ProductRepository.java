package net.company.hookahstore.repository;

import net.company.hookahstore.entity.Category;
import net.company.hookahstore.entity.Producer;
import net.company.hookahstore.entity.Product;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

@RepositoryDefinition(domainClass = Product.class,idClass = Long.class)
public interface ProductRepository {
    List<Product> showProducts(int page,int limit);
    List<Product> findAllProduct();
    Product getProductById(Long id);
    List<Product> getProductsByCategory(Category category);
    List<Product> getProductsByProducer(Producer producer);

}
