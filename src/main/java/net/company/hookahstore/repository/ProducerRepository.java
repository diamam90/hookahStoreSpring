package net.company.hookahstore.repository;

import net.company.hookahstore.entity.Category;
import net.company.hookahstore.entity.Producer;
import net.company.hookahstore.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ProducerRepository extends JpaRepository<Producer,Long> {

    Producer getProducerById(Long id);

    @Query(value = "select pr.* from product p,category c, producer pr where p.id_category=c.id and p.id_producer=pr.id" +
            " and c.id=? group by pr.id",
            nativeQuery = true)
    List<Producer> getProducersByCategory(@Param("c.id_category")Long id);

}
