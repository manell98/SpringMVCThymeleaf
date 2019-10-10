package br.com.mvc.spring.thymeleaf.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mvc.spring.thymeleaf.domain.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
	
	List<Funcionario> findByNomeContaining(String nome);

	List<Funcionario> findByCargoId(Integer id);

	List<Funcionario> findByDataEntradaBetween(LocalDate entrada, LocalDate saida);

	List<Funcionario> findByDataEntrada(LocalDate entrada);

	List<Funcionario> findByDataSaida(LocalDate saida);

}
