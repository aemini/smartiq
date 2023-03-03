package io.smartiq.aemini.proxy.controllers;

import io.smartiq.aemini.commons.dtos.Category;
import io.smartiq.aemini.commons.dtos.Product;
import io.smartiq.aemini.commons.models.ApiResponse;
import io.smartiq.aemini.commons.models.SuccessResponse;
import io.smartiq.aemini.proxy.clients.CategoryClient;
import io.smartiq.aemini.proxy.clients.ProductClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/categories")
public class CategoryController extends AbstractController {

	private final CategoryClient categoryClient;
	private final ProductClient productClient;

	public CategoryController(CategoryClient categoryClient,
							  ProductClient productClient) {
		this.categoryClient = categoryClient;
		this.productClient = productClient;
	}

	@GetMapping(value="", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse> list() {
		return ResponseEntity.ok(SuccessResponse.content(categoryClient.list().getContent()));
	}

	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse> show(@PathVariable("id") Long id) {
		return ResponseEntity.ok(SuccessResponse.content(categoryClient.show(id).getContent()));
	}

	@PostMapping(value="", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse> store(@RequestBody Category category) {
		return ResponseEntity.status(HttpStatus.CREATED).body(SuccessResponse.content(categoryClient.save(category).getContent()));
	}

	@PutMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse> update(@PathVariable("id") Long id,
											  @RequestBody Category category) {
		return ResponseEntity.ok(SuccessResponse.content(categoryClient.save(id, category).getContent()));
	}

	@DeleteMapping(value="{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		categoryClient.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

	@PostMapping(value="/{id}/products")
	public ResponseEntity<ApiResponse> addProduct(@PathVariable("id") Long categoryId,
												  @RequestBody Product product) {
		var category = categoryClient.show(categoryId);
		product.setCategory(category.getRequiredLink("self").getHref());
		return ResponseEntity.ok(SuccessResponse.content(productClient.save(product).getContent()));
	}

}
