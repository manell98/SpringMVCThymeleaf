package br.com.mvc.spring.thymeleaf.dao;

import org.springframework.stereotype.Repository;

import br.com.mvc.spring.thymeleaf.domain.Cargo;

@Repository
public class CargoDaoRepository extends DAO<Cargo, Integer> implements CargoDao {

}
