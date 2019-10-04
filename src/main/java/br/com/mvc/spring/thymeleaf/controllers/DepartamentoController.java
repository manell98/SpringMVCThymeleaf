package br.com.mvc.spring.thymeleaf.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.mvc.spring.thymeleaf.domain.Departamento;
import br.com.mvc.spring.thymeleaf.services.DepartamentoService;

@Controller
@RequestMapping("/departamentos")
public class DepartamentoController {
	
	@Autowired
	private DepartamentoService departamentoService;

	@RequestMapping("cadastrar")
	public String cadastrar(Departamento departamento) {	
		return "departamento/cadastro";
	}
	
	@RequestMapping("listar")
	public String listar(ModelMap model) {	
		model.addAttribute("departamentos", departamentoService.findAll());
		
		return "departamento/lista";
	}
	
	@RequestMapping(value= "salvar", method=RequestMethod.POST)
	public String salvar(Departamento departamento) {
		departamentoService.insert(departamento);
		
		return "redirect:/departamentos/listar";
	}
	
	@RequestMapping("editar/{id}")
	public String find(@PathVariable("id") Integer id, ModelMap model) {
		model.addAttribute("departamento", departamentoService.find(id));
		
		return "/departamento/cadastro";
	}
	
	@RequestMapping(value= "editar", method=RequestMethod.POST)
	public String editar(Departamento departamento) {
		departamentoService.update(departamento);
		
		return "redirect:/departamentos/listar";
	}
	
}









