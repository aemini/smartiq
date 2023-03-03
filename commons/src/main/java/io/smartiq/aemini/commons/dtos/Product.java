package io.smartiq.aemini.commons.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Product {

	@NotNull
	private String name;

	@NotNull
	private Double price;

	@NotNull
	private Integer stockCount;

	private String category;

}
