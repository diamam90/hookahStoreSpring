package net.company.hookahstore.repository;

import net.company.hookahstore.entity.Category;
import net.company.hookahstore.entity.Producer;
import net.company.hookahstore.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

@RepositoryDefinition(domainClass = Product.class,idClass = Long.class)
public interface ProductRepository {

    @Query(value = "select p from #{#entityName} p")
    List<Product> findAll();

    Product getProductById(Long id);
    List<Product> getProductsByCategory(Category category);
    List<Product> getProductsByProducer(Producer producer);

}
