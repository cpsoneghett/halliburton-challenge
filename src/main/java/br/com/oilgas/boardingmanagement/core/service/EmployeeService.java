package br.com.oilgas.boardingmanagement.core.service;

import br.com.oilgas.boardingmanagement.core.domain.dto.EmployeeRequestDto;
import br.com.oilgas.boardingmanagement.core.domain.dto.EmployeeResponseDto;
import br.com.oilgas.boardingmanagement.exception.CompanyNotFoundException;
import br.com.oilgas.boardingmanagement.exception.WorkingFunctionNotFoundException;

public interface EmployeeService {

	EmployeeResponseDto create(EmployeeRequestDto employee)
			throws WorkingFunctionNotFoundException, CompanyNotFoundException;

}
