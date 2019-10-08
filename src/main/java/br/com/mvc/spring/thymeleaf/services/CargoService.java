package br.com.mvc.spring.thymeleaf.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.mvc.spring.thymeleaf.domain.Cargo;
import br.com.mvc.spring.thymeleaf.repositories.CargoRepository;
import br.com.mvc.spring.thymeleaf.services.exceptions.ObjectNotFoundException;

@Service
public class CargoService {

	@Autowired
	CargoRepository cargoRepository;
	
	public void insert(Cargo obj) {
		cargoRepository.save(obj);
	}
	
	public Cargo update(Cargo obj) {
		Cargo newObj = find(obj.getId());
		updateData(newObj, obj);
		return cargoRepository.save(newObj);
	}
	
	private void updateData(Cargo newObj, Cargo obj) {
		newObj.setNome(obj.getNome());
	}
	
	public void delete(Integer id) {
		find(id);		
		cargoRepository.deleteById(id);
	}
	
	@Transactional(readOnly = true)
	public Cargo find(Integer id){
		Optional<Cargo> obj = cargoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: "  + Cargo.class.getName()));
	}
	
	@Transactional(readOnly = true)
	public List<Cargo> findAll() {
		return cargoRepository.findAll();
	}

	public boolean cargoTemFuncionarios(Integer id) {
		if(find(id).getFuncionarios().isEmpty()) {
			return false;
		}
		return true;
	}
	
}
