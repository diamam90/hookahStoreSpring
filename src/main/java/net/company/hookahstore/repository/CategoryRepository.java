package net.company.hookahstore.repository;

import net.company.hookahstore.entity.Category;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

@RepositoryDefinition(domainClass = Category.class,idClass=Long.class)
public interface CategoryRepository {
    List<Category> getAllCategories();
    Category getCategoryById(Long id);
}
