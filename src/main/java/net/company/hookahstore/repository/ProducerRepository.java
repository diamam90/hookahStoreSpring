package net.company.hookahstore.repository;

import net.company.hookahstore.entity.Producer;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

@RepositoryDefinition(domainClass = Producer.class,idClass = Long.class)
public interface ProducerRepository {
    List<Producer> findAllProducers();
    Producer getProducerById(Long id);


}
