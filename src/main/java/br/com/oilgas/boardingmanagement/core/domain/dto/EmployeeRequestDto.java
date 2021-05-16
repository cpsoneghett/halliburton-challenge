package br.com.oilgas.boardingmanagement.core.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeRequestDto {

	private String firstName;

	private String lastName;

	private Long idCompany;

	private Long idFunction;

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

}
