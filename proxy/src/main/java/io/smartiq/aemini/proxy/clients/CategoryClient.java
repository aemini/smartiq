package io.smartiq.aemini.proxy.clients;

import io.smartiq.aemini.commons.dtos.Category;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="product-instance-management/categories")
public interface CategoryClient {

	@GetMapping(value="")
	CollectionModel<EntityModel<Category>> list();

	@GetMapping(value="/{id}")
	EntityModel<Category> show(@PathVariable("id") Long id);

	@PostMapping(value="")
	EntityModel<Category> save(@RequestBody Category category);

	@PutMapping(value="/{id}")
	EntityModel<Category> save(@PathVariable("id") Long id, @RequestBody Category category);

	@DeleteMapping(value="/{id}")
	void delete(@PathVariable("id") long id);

}
