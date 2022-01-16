package net.company.hookahstore.repository;

import net.company.hookahstore.entity.Producer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

@RepositoryDefinition(domainClass = Producer.class,idClass = Long.class)
public interface ProducerRepository {
    @Query(value = "select p from #{#entityName} p")
    List<Producer> findAll();
    Producer getProducerById(Long id);


}
