package br.com.oilgas.boardingmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.oilgas.boardingmanagement.core.domain.dto.EmployeeRequestDto;
import br.com.oilgas.boardingmanagement.core.domain.dto.EmployeeResponseDto;
import br.com.oilgas.boardingmanagement.core.service.EmployeeService;
import br.com.oilgas.boardingmanagement.exception.CompanyNotFoundException;
import br.com.oilgas.boardingmanagement.exception.WorkingFunctionNotFoundException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/employee")
@ApiResponses(value = { @ApiResponse(code = 200, message = "Request successful"),
		@ApiResponse(code = 400, message = "Request error") })
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@ApiOperation(value = "Create employee", produces = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmployeeResponseDto> create(@RequestBody EmployeeRequestDto employee)
			throws WorkingFunctionNotFoundException, CompanyNotFoundException {

		EmployeeResponseDto response = employeeService.create(employee);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
}
