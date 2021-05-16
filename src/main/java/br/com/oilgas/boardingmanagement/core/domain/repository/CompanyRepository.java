package br.com.oilgas.boardingmanagement.core.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.oilgas.boardingmanagement.core.domain.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long>  {

}
