package io.smartiq.aemini.proxy.controllers;

import feign.FeignException;
import io.smartiq.aemini.commons.models.ApiResponse;
import io.smartiq.aemini.commons.models.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public abstract class AbstractController {

	// Muhtelif hata mesajlari burada turetilebilir
	@ExceptionHandler(value=FeignException.NotFound.class)
	protected ResponseEntity<ApiResponse> errorHandler() {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponse.message("Resource not found"));
	}

}
