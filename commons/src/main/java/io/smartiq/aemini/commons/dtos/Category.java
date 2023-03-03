package io.smartiq.aemini.commons.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Category {

	@NotNull
	private String name;
	private String products;

}
