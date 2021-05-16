package br.com.oilgas.boardingmanagement.exception.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.oilgas.boardingmanagement.exception.CompanyNotFoundException;
import br.com.oilgas.boardingmanagement.exception.WorkingFunctionNotFoundException;

@ControllerAdvice
public class BoardingApiExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		String userMessage = messageSource.getMessage("invalid.message", null, LocaleContextHolder.getLocale());
		String developerMessage = ex.getCause() != null ? ex.getCause().toString() : ex.toString();

		List<Error> errors = Arrays.asList(new Error(userMessage, developerMessage));

		return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<Error> errors = criarListaDeErros(ex.getBindingResult());

		return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
	}

	private List<Error> criarListaDeErros(BindingResult bindingResult) {
		List<Error> errors = new ArrayList<>();

		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			String userMessage = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			String developerMessage = fieldError.toString();

			errors.add(new Error(userMessage, developerMessage));
		}

		return errors;
	}

	@ExceptionHandler({ CompanyNotFoundException.class })
	public ResponseEntity<Object> handleCompanyNotFoundException(CompanyNotFoundException ex, WebRequest request) {

		String userMessage = messageSource.getMessage("company-not-found", null, LocaleContextHolder.getLocale());
		String developerMessage = ex.getCause() != null ? ex.getCause().toString() : ex.toString();

		List<Error> errors = Arrays.asList(new Error(userMessage, developerMessage));

		return ResponseEntity.badRequest().body(errors);
	}

	@ExceptionHandler({ WorkingFunctionNotFoundException.class })
	public ResponseEntity<Object> handleWorkingFunctionNotFoundException(WorkingFunctionNotFoundException ex,
			WebRequest request) {

		String userMessage = messageSource.getMessage("working-function-not-found", null,
				LocaleContextHolder.getLocale());
		String developerMessage = ex.getCause() != null ? ex.getCause().toString() : ex.toString();

		List<Error> errors = Arrays.asList(new Error(userMessage, developerMessage));

		return ResponseEntity.badRequest().body(errors);
	}

	public static class Error {

		private String userMessage;
		private String developerMessage;

		public Error(String userM, String developerM) {
			super();
			this.userMessage = userM;
			this.developerMessage = developerM;
		}

		public String getDeveloperMessage() {
			return developerMessage;
		}

		public String getUserMessage() {
			return userMessage;
		}

	}
}
