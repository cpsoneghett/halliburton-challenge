package br.com.oilgas.boardingmanagement.core.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.oilgas.boardingmanagement.core.domain.dto.EmployeeRequestDto;
import br.com.oilgas.boardingmanagement.core.domain.dto.EmployeeResponseDto;
import br.com.oilgas.boardingmanagement.core.domain.entity.Company;
import br.com.oilgas.boardingmanagement.core.domain.entity.Employee;
import br.com.oilgas.boardingmanagement.core.domain.entity.WorkingFunction;
import br.com.oilgas.boardingmanagement.core.domain.repository.CompanyRepository;
import br.com.oilgas.boardingmanagement.core.domain.repository.EmployeeRepository;
import br.com.oilgas.boardingmanagement.core.domain.repository.WorkingFunctionRepository;
import br.com.oilgas.boardingmanagement.core.service.EmployeeService;
import br.com.oilgas.boardingmanagement.exception.CompanyNotFoundException;
import br.com.oilgas.boardingmanagement.exception.WorkingFunctionNotFoundException;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private WorkingFunctionRepository functionRepository;

	@Override
	public EmployeeResponseDto create(EmployeeRequestDto employeeDto)
			throws WorkingFunctionNotFoundException, CompanyNotFoundException {

		Company c = findCompanyById(employeeDto.getIdCompany());

		WorkingFunction f = findFunctionById(employeeDto.getIdFunction());

		Employee e = new Employee(employeeDto, c.getId(), f.getId());

		employeeRepository.save(e);

		EmployeeResponseDto response = new EmployeeResponseDto();
		response.setId(e.getId());
		response.setName(e.getFirstName().concat(" ").concat(e.getLastName()));
		response.setCompany(c.getName());
		response.setFunction(f.getName());

		return response;

	}

	private WorkingFunction findFunctionById(Long id) throws WorkingFunctionNotFoundException {

		Optional<WorkingFunction> f = functionRepository.findById(id);

		if (f.isEmpty())
			throw new WorkingFunctionNotFoundException();

		return f.get();

	}

	private Company findCompanyById(Long id) throws CompanyNotFoundException {

		Optional<Company> c = companyRepository.findById(id);

		if (c.isEmpty())
			throw new CompanyNotFoundException();

		return c.get();

	}

}
