package io.smartiq.aemini.proxy.clients;

import io.smartiq.aemini.commons.dtos.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name="product-instance-management/products")
public interface ProductClient {

	@GetMapping(value="")
	CollectionModel<EntityModel<Product>> list();

	@GetMapping(value="/{id}")
	EntityModel<Product> show(@PathVariable("id") Long id);

	@PostMapping(value="")
	EntityModel<Product> save(@RequestBody Product product);

	@PutMapping(value="/{id}")
	EntityModel<Product> save(@PathVariable("id") Long id, @RequestBody Product product);

	@DeleteMapping(value="/{id}")
	void delete(@PathVariable("id") long id);

}
