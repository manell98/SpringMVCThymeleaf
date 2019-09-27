package br.com.mvc.spring.thymeleaf.dao;

import java.util.List;

import br.com.mvc.spring.thymeleaf.domain.Cargo;

public interface CargoDao {
	
	void save(Cargo cargo);
	
	void update(Cargo cargo);
	
	void delete(Integer id);
	
	Cargo findById(Integer id);
	
	List<Cargo> findAll();

}
