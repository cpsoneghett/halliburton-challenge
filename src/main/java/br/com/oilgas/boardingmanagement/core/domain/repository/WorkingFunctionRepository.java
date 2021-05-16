package br.com.oilgas.boardingmanagement.core.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.oilgas.boardingmanagement.core.domain.entity.WorkingFunction;

public interface WorkingFunctionRepository extends JpaRepository<WorkingFunction, Long> {

}
