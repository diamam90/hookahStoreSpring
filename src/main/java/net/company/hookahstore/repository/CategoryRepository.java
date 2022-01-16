package net.company.hookahstore.repository;

import net.company.hookahstore.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;


public interface CategoryRepository extends JpaRepository<Category,Long> {

    Category findCategoryById(Long id);
}
