package io.smartiq.aemini.commons.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper=true)
public class SuccessResponse extends ApiResponse {

	private final Object data;

	private SuccessResponse(Object data) {
		super(true);
		this.data = data;
	}

	public static SuccessResponse content(Object data) {
		return new SuccessResponse(data);
	}

}
