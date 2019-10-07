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
	private DepartamentoService service;

	@RequestMapping("cadastrar")
	public String cadastrar(Departamento departamento) {	
		return "departamento/cadastro";
	}
	
	@RequestMapping("listar")
	public String listar(ModelMap model) {	
		model.addAttribute("departamentos", service.findAll());
		
		return "departamento/lista";
	}
	
	@RequestMapping(value= "salvar", method=RequestMethod.POST)
	public String salvar(Departamento departamento, ModelMap model) {
		model.addAttribute("success", "Departamento cadastrado com sucesso!");
		service.insert(departamento);
		
		return listar(model);
	}
	
	@RequestMapping("editar/{id}")
	public String find(@PathVariable("id") Integer id, ModelMap model) {
		model.addAttribute("departamento", service.find(id));
		
		return "/departamento/cadastro";
	}
	
	@RequestMapping(value="editar", method=RequestMethod.POST)
	public String editar(Departamento departamento, ModelMap model) {
		service.update(departamento);
		model.addAttribute("success", "Departamento editado com sucesso!");
		
		return listar(model);
	}
	
	@RequestMapping("excluir/{id}")
	public String excluir(@PathVariable("id") Integer id, ModelMap model) {
		
		if (service.depertamentoTemCargos(id)) {
			model.addAttribute("fail", "Departamento não removido, possui cargo(s) vinculado(s) a ele!");
		} else {
			service.delete(id);
			model.addAttribute("success", "Departamento excluído com sucesso!");
		}
		
		return listar(model);
	}
	
}