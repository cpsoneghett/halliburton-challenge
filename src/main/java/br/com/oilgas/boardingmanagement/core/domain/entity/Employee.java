package br.com.oilgas.boardingmanagement.core.domain.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.beans.BeanUtils;

import br.com.oilgas.boardingmanagement.core.domain.dto.EmployeeRequestDto;

@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String firstName;

	private String lastName;

	private Long idCompany;

	private Long idFunction;

	private Date inclusionDate;

	public Employee(EmployeeRequestDto employeeDto, Long idCompany, Long idFunction) {

		BeanUtils.copyProperties(employeeDto, this);
		this.idCompany = idCompany;
		this.idFunction = idFunction;
		this.inclusionDate = new Date();

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getIdCompany() {
		return idCompany;
	}

	public void setIdCompany(Long idCompany) {
		this.idCompany = idCompany;
	}

	public Long getIdFunction() {
		return idFunction;
	}

	public void setIdFunction(Long idFunction) {
		this.idFunction = idFunction;
	}

	public Date getInclusionDate() {
		return inclusionDate;
	}

	public void setInclusionDate(Date inclusionDate) {
		this.inclusionDate = inclusionDate;
	}

}
