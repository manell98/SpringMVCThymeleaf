package br.com.mvc.spring.thymeleaf.dao;

import org.springframework.stereotype.Repository;

import br.com.mvc.spring.thymeleaf.domain.Departamento;

@Repository
public class DepartamentoDaoRepository extends DAO<Departamento, Integer> implements DepartamentoDao {

}
