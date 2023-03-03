package io.smartiq.aemini.productinstancemanagement.repositories;

import io.smartiq.aemini.productinstancemanagement.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
