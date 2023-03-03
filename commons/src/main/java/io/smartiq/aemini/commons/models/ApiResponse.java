package io.smartiq.aemini.commons.models;

import lombok.Data;

@Data
public abstract class ApiResponse {

	private final boolean success;

	protected ApiResponse(boolean success) {
		this.success = success;
	}

}
