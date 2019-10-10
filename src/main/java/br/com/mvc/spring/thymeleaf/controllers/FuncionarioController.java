package br.com.mvc.spring.thymeleaf.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.mvc.spring.thymeleaf.domain.Cargo;
import br.com.mvc.spring.thymeleaf.domain.Funcionario;
import br.com.mvc.spring.thymeleaf.domain.enums.UF;
import br.com.mvc.spring.thymeleaf.services.CargoService;
import br.com.mvc.spring.thymeleaf.services.FuncionarioService;

@Controller
@RequestMapping("/funcionarios")
public class FuncionarioController {
	
	@Autowired
	private FuncionarioService service;
	
	@Autowired
	private CargoService cargoService;

	@RequestMapping("cadastrar")
	public String cadastrar(Funcionario funcionario) {		
		return "funcionario/cadastro";
	}
	
	@RequestMapping("listar")
	public String listar(ModelMap model) {	
		model.addAttribute("funcionarios", service.findAll());
		
		return "funcionario/lista";
	}
	
	@RequestMapping(value= "salvar", method=RequestMethod.POST)
	public String salvar(Funcionario funcionario, RedirectAttributes attr) {
		service.insert(funcionario);
		attr.addFlashAttribute("success", "Funcionário cadastrado com sucesso!");
		
		return "redirect:/funcionarios/listar";
	}
	
	@ModelAttribute("cargos")
	public List<Cargo> listaCargos() {	
		return cargoService.findAll();
	}
	
	@ModelAttribute("ufs")
	public UF[] getUfs() {	
		return UF.values();
	}
	
	@RequestMapping("editar/{id}")
	public String find(@PathVariable("id") Integer id, ModelMap model) {
		model.addAttribute("funcionario", service.find(id));
		
		return "/funcionario/cadastro";
	}
	
	@RequestMapping(value="editar", method=RequestMethod.POST)
	public String editar(Funcionario funcionario, RedirectAttributes attr) {
		service.update(funcionario);
		attr.addFlashAttribute("success", "Funcionário editado com sucesso!");
		
		return "redirect:/funcionarios/listar";
	}
	
	@RequestMapping("excluir/{id}")
	public String excluir(@PathVariable("id") Integer id, RedirectAttributes attr) {
		service.delete(id);
		attr.addFlashAttribute("success", "Cargo excluído com sucesso!");
	
		return "redirect:/funcionarios/listar";
	}
	
	@RequestMapping("buscar/nome")
	public String findNome(@RequestParam("nome") String nome, ModelMap model) {
		model.addAttribute("funcionarios", service.findByNome(nome));
		
		return "/funcionario/lista";
	}
	
	@RequestMapping("buscar/cargo")
	public String findNome(@RequestParam("id") Integer id, ModelMap model) {
		model.addAttribute("funcionarios", service.findByCargo(id));
		
		return "/funcionario/lista";
	}
	
}
