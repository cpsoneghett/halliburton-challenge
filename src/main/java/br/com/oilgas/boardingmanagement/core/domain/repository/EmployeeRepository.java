package br.com.oilgas.boardingmanagement.core.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.oilgas.boardingmanagement.core.domain.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
