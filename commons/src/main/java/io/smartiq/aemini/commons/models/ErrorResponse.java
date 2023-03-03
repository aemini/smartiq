package io.smartiq.aemini.commons.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper=true)
public class ErrorResponse extends ApiResponse {

	private final String message;

	private ErrorResponse(String message) {
		super(false);
		this.message = message;
	}

	public static ErrorResponse message(String message) {
		return new ErrorResponse(message);
	}

}
