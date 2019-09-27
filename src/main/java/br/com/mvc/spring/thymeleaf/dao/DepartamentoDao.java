package br.com.mvc.spring.thymeleaf.dao;

import java.util.List;

import br.com.mvc.spring.thymeleaf.domain.Departamento;

public interface DepartamentoDao {
	
	void save(Departamento departamento);
	
	void update(Departamento departamento);
	
	void delete(Integer id);
	
	Departamento findById(Integer id);
	
	List<Departamento> findAll();

}
