package net.company.hookahstore.repository;

import net.company.hookahstore.entity.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

@RepositoryDefinition(domainClass = Category.class,idClass=Long.class)
public interface CategoryRepository {
    @Query(value = "select c from #{#entityName} c")
    List<Category> findAll();
    Category findCategoryById(Long id);
}
