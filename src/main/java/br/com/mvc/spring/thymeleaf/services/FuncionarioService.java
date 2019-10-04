package br.com.mvc.spring.thymeleaf.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mvc.spring.thymeleaf.domain.Funcionario;
import br.com.mvc.spring.thymeleaf.repositories.FuncionarioRepository;
import br.com.mvc.spring.thymeleaf.services.exceptions.DataIntegrityViolationException;
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
	}
	
	public void delete(Integer id) {
		find(id);		
		try {
			funcionarioRepository.deleteById(id);
		}
		catch(Exception ex) {
			throw new DataIntegrityViolationException("Não é possível excluir o cliente porque há pedidos relacionados a ele");
		}
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
	
}
