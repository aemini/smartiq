package io.smartiq.aemini.productinstancemanagement.repositories;

import io.smartiq.aemini.productinstancemanagement.entities.Category;
import io.smartiq.aemini.productinstancemanagement.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findAllByCategory(Category category);

}
