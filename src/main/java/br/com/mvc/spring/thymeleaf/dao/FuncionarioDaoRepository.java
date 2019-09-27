package br.com.mvc.spring.thymeleaf.dao;

import org.springframework.stereotype.Repository;

import br.com.mvc.spring.thymeleaf.domain.Funcionario;

@Repository
public class FuncionarioDaoRepository extends DAO<Funcionario, Integer> implements FuncionarioDao {

}
