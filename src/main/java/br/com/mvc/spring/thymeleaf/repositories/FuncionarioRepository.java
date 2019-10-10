package br.com.mvc.spring.thymeleaf.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.mvc.spring.thymeleaf.domain.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
	
	@Query("select f from Funcionario f where f.nome like %:nome%")
	List<Funcionario> findByNome(@Param("nome") String nome);

	@Query("select f from Funcionario f where f.cargo.id = :id")
	List<Funcionario> findByCargo(@Param("id") Integer id);

}
