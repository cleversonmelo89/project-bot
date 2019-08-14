package br.com.bot.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.bot.domain.ApiError;

@RestControllerAdvice
public class CustomExceptionResponseHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = ResponseStatusException.class)
	public ResponseEntity<ApiError> customHandler(ResponseStatusException ex, WebRequest request) {

		List<String> messageErrors = new ArrayList<>();
		messageErrors.add(ex.getReason());

		ApiError errors = ApiError.builder().status(ex.getStatus()).message(ex.getLocalizedMessage())
				.detail(messageErrors).code(ex.getStatus().value()).build();

		return ResponseEntity.status(ex.getStatus()).body(errors);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<String> messageErros = ex.getBindingResult().getFieldErrors().stream()
				.map(error -> buildMessage(error.getField(), error.getDefaultMessage())).collect(Collectors.toList());

		ApiError errors = ApiError.builder().message(ex.getLocalizedMessage()).detail(messageErros).code(status.value())
				.status(status).build();

		return ResponseEntity.status(status).body(errors);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<String> messageErrors = new ArrayList<>();
		messageErrors.add(ex.getMessage());

		ApiError errors = ApiError.builder().message(ex.getLocalizedMessage()).detail(messageErrors)
				.code(status.value()).status(status).build();

		return ResponseEntity.status(status).body(errors);
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<String> messageErrors = new ArrayList<>();
		messageErrors.add(ex.getMessage());

		ApiError errors = ApiError.builder().message(ex.getLocalizedMessage()).detail(messageErrors)
				.code(status.value()).status(status).build();

		return ResponseEntity.status(status).body(errors);
	}

	private String buildMessage(String field, String message) {
		return field + " " + message;
	}

}
