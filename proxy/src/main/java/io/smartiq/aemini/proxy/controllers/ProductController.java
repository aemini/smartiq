package io.smartiq.aemini.proxy.controllers;

import io.smartiq.aemini.commons.dtos.Product;
import io.smartiq.aemini.commons.models.ApiResponse;
import io.smartiq.aemini.commons.models.ErrorResponse;
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

import java.util.Objects;

@RestController
@RequestMapping(value="/products")
public class ProductController extends AbstractController {

	private final CategoryClient categoryClient;
	private final ProductClient productClient;

	public ProductController(CategoryClient categoryClient,
							 ProductClient productClient) {
		this.categoryClient = categoryClient;
		this.productClient = productClient;
	}

	@GetMapping(value="", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse> list() {
		return ResponseEntity.ok(SuccessResponse.content(productClient.list().getContent()));
	}

	@GetMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse> show(@PathVariable("id") Long id) {
		return ResponseEntity.ok(SuccessResponse.content(productClient.show(id).getContent()));
	}

	@PostMapping(value="", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse> store(@RequestBody Product product) {
		if (Objects.isNull(product.getCategory())) {
			// i18n ile headerden alinan locale vasitasiyla ilgili dilde hata mesaji dondurulebilir
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(ErrorResponse.message("category field is required"));
		}
		var category = categoryClient.show(Long.valueOf(product.getCategory()));
		product.setCategory(category.getRequiredLink("self").getHref());
		return ResponseEntity.status(HttpStatus.CREATED).body(SuccessResponse.content(productClient.save(product).getContent()));
	}

	@PutMapping(value="/{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ApiResponse> update(@PathVariable("id") Long id,
											  @RequestBody Product product) {
		return ResponseEntity.ok(SuccessResponse.content(productClient.save(id, product).getContent()));
	}

	@DeleteMapping(value="{id}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
		productClient.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}

}
