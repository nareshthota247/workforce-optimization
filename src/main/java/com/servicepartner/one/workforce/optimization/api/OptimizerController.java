package com.servicepartner.one.workforce.optimization.api;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.servicepartner.one.workforce.optimization.exceptionhandeling.ErrorResponse;
import com.servicepartner.one.workforce.optimization.model.CleaningTasks;
import com.servicepartner.one.workforce.optimization.model.StaffingLevel;
import com.servicepartner.one.workforce.optimization.service.OptimizerService;


@RestController
@RequestMapping(value = "/api")
public class OptimizerController {
	
	@Autowired
	OptimizerService optimizerService;
	
	@PostMapping("/optimize")
	public ResponseEntity<List<StaffingLevel>> optimize(@Valid @RequestBody CleaningTasks tasks) {
		
		return ResponseEntity.ok(optimizerService.optimiseStaff(tasks));
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
//		logger.error("Exception MethodArgumentNotValidException :: {} ", ex.getMessage());
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach(error -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorResponse> handleValidationExceptions(HttpMessageNotReadableException ex) {
//		logger.error("Exception HttpMessageNotReadableException :: {} ", ex.getMessage());
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(Integer.valueOf(1002));
		error.setErrorDesc(ex.getMessage());
		error.setTimestamp(new Date());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

}