package br.com.mvc.spring.thymeleaf.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.mvc.spring.thymeleaf.domain.Cargo;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Integer> {

}
