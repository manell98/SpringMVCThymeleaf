package br.com.mvc.spring.thymeleaf.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mvc.spring.thymeleaf.domain.Funcionario;
import br.com.mvc.spring.thymeleaf.repositories.FuncionarioRepository;
import br.com.mvc.spring.thymeleaf.services.exceptions.ObjectNotFoundException;

@Service
public class FuncionarioService {

	@Autowired
	FuncionarioRepository funcionarioRepository;
	
	public void insert(Funcionario obj) {
		funcionarioRepository.save(obj);
	}
	
	public Funcionario update(Funcionario obj) {
		Funcionario newObj = find(obj.getId());
		updateData(newObj, obj);
		return funcionarioRepository.save(newObj);
	}
	
	private void updateData(Funcionario newObj, Funcionario obj) {
		newObj.setNome(obj.getNome());
		newObj.setSalario(obj.getSalario());
		newObj.setDataEntrada(obj.getDataEntrada());
		newObj.setDataSaida(obj.getDataSaida());
		newObj.setEndereco(obj.getEndereco());
		newObj.setCargo(obj.getCargo());
	}
	
	public void delete(Integer id) {
		find(id);		
		funcionarioRepository.deleteById(id);
	}
	
	@Transactional(readOnly = true)
	public Funcionario find(Integer id){
		Optional<Funcionario> obj = funcionarioRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: "  + Funcionario.class.getName()));
	}
	
	@Transactional(readOnly = true)
	public List<Funcionario> findAll() {
		return funcionarioRepository.findAll();
	}

	@Transactional(readOnly=true)
	public List<Funcionario> findByNomeContaining(String nome) {
		return funcionarioRepository.findByNomeContaining(nome);
	}

	@Transactional(readOnly=true)
	public List<Funcionario> findByCargoId(Integer id) {
		return funcionarioRepository.findByCargoId(id);
	}

	@Transactional(readOnly=true)
	public List<Funcionario> findByDatas(LocalDate entrada, LocalDate saida) {
		
		if(entrada != null && saida != null) {
			return funcionarioRepository.findByDataEntradaBetween(entrada,saida);
		} 
		else if(entrada != null) {
			return funcionarioRepository.findByDataEntrada(entrada);
		} 
		else if (saida != null) {
			return funcionarioRepository.findByDataSaida(saida);
		}
		
		return new ArrayList<>();
		
	}
	
}
