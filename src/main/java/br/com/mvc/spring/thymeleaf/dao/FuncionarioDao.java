package br.com.mvc.spring.thymeleaf.dao;

import java.util.List;

import br.com.mvc.spring.thymeleaf.domain.Funcionario;

public interface FuncionarioDao {
	
	void save(Funcionario Funcionario);
	
	void update(Funcionario Funcionario);
	
	void delete(Integer id);
	
	Funcionario findById(Integer id);
	
	List<Funcionario> findAll();

}
