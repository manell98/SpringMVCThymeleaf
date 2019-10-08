package br.com.mvc.spring.thymeleaf.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mvc.spring.thymeleaf.domain.Departamento;
import br.com.mvc.spring.thymeleaf.repositories.DepartamentoRepository;
import br.com.mvc.spring.thymeleaf.services.exceptions.ObjectNotFoundException;

@Service
public class DepartamentoService {

	@Autowired
	DepartamentoRepository departamentoRepository;
	
	public void insert(Departamento obj) {
		departamentoRepository.save(obj);
	}
	
	public Departamento update(Departamento obj) {
		Departamento newObj = find(obj.getId());
		updateData(newObj, obj);
		return departamentoRepository.save(newObj);
	}
	
	private void updateData(Departamento newObj, Departamento obj) {
		newObj.setNome(obj.getNome());
	}
	
	public void delete(Integer id) {
		find(id);		
		departamentoRepository.deleteById(id);
	}
	
	@Transactional(readOnly = true)
	public Departamento find(Integer id){
		Optional<Departamento> obj = departamentoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: "  + Departamento.class.getName()));
	}
	
	@Transactional(readOnly = true)
	public List<Departamento> findAll() {
		return departamentoRepository.findAll();
	}
	
	public boolean depertamentoTemCargos(Integer id) {
		if(find(id).getCargos().isEmpty()) {
			return false;
		}
		return true;
	}	
}
