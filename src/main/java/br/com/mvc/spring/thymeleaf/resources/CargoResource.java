package br.com.mvc.spring.thymeleaf.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.mvc.spring.thymeleaf.domain.Cargo;
import br.com.mvc.spring.thymeleaf.domain.Departamento;
import br.com.mvc.spring.thymeleaf.services.CargoService;
import br.com.mvc.spring.thymeleaf.services.DepartamentoService;

@Controller
@RequestMapping("/cargos")
public class CargoResource {
	
	@Autowired
	private CargoService service;
	
	@Autowired
	private DepartamentoService departamentoService;

	@RequestMapping("cadastrar")
	public String cadastrar(Cargo cargo) {
		
		return "cargo/cadastro";
	}
	
	@RequestMapping("listar")
	public String listar(ModelMap model) {	
		model.addAttribute("cargos", service.findAll());
		
		return "cargo/lista";
	}
	
	@RequestMapping(value= "salvar", method=RequestMethod.POST)
	public String salvar(Cargo cargo, RedirectAttributes attr) {
		service.insert(cargo);
		attr.addFlashAttribute("success", "Cargo cadastrado com sucesso!");
		
		return "redirect:/cargos/listar";
	}
	
	@ModelAttribute("departamentos")
	public List<Departamento> listaDepartamentos() {		
		return departamentoService.findAll();
	}
	
	@RequestMapping("editar/{id}")
	public String find(@PathVariable("id") Integer id, ModelMap model) {
		model.addAttribute("cargo", service.find(id));
		
		return "/cargo/cadastro";
	}
	
	@RequestMapping(value="editar", method=RequestMethod.POST)
	public String editar(Cargo cargo, RedirectAttributes attr) {
		service.update(cargo);
		attr.addFlashAttribute("success", "Cargo editado com sucesso!");
		
		return "redirect:/cargos/listar";
	}
	
	@RequestMapping("excluir/{id}")
	public String excluir(@PathVariable("id") Integer id, RedirectAttributes attr) {
		
		if (service.cargoTemFuncionarios(id)) {
			attr.addFlashAttribute("fail", "Cargo não removido, possui funcionário(s) vinculado(s) a ele!");
		} else {
			service.delete(id);
			attr.addFlashAttribute("success", "Cargo excluído com sucesso!");
		}
		
		return "redirect:/cargos/listar";
	}
	
}
