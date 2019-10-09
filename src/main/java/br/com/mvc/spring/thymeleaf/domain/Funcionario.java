package br.com.mvc.spring.thymeleaf.domain;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

@Entity
public class Funcionario extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	private String nome;
	
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	@Column(columnDefinition = "DECIMAL (7,2) DEFAULT 0.00")
	private BigDecimal salario;
	
	@DateTimeFormat(iso = ISO.DATE)
	private Calendar dataEntrada;
	
	@DateTimeFormat(iso = ISO.DATE)
	private Calendar dataSaida;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Endereco endereco;
	
	@ManyToOne
	private Cargo cargo;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public Calendar getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(Calendar dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public Calendar getDataSaida() {
		return dataSaida;
	}

	public void setDataSaida(Calendar dataSaida) {
		this.dataSaida = dataSaida;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}	
}
