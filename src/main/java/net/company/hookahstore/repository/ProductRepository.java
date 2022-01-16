package net.company.hookahstore.repository;

import net.company.hookahstore.entity.Category;
import net.company.hookahstore.entity.Producer;
import net.company.hookahstore.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ProductRepository extends PagingAndSortingRepository<Product,Long> {

    Product getProductById(Long id);
    List<Product> getProductsByCategory(Category category);
    List<Product> getProductsByProducer(Producer producer);
    Page<Product> findAllByCategory_IdAndProducer_Id(Long idCategory, Long idProducer,Pageable pageable);
    Page<Product> findAllByCategory_Id(Long idCategory, Pageable pageable);

    @Query(value = "select * from product where name ilike ? or description ilike ?",nativeQuery=true)
    Page<Product> findAllProductsBySearchQuery(String name, String description, Pageable pageable);


    @Query(value = "select * from product where id_category=? and (name ilike ? or description ilike ?)", nativeQuery=true)
    Page<Product> findAllProductsBySearchQueryWithChosenCategory(Long idCategory, String name, String description, Pageable pageable);
}
