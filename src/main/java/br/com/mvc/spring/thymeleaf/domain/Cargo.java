package br.com.mvc.spring.thymeleaf.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Cargo extends AbstractEntity {
	private static final long serialVersionUID = 1L;
	
	public String nome;
	
	@ManyToOne
	private Departamento departamento;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
}
