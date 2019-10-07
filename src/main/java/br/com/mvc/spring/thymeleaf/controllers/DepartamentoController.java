package br.com.mvc.spring.thymeleaf.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String salvar(Departamento departamento, RedirectAttributes attr) {
		attr.addFlashAttribute("sucess", "Departamento cadastrado com sucesso!");
		service.insert(departamento);
		
		return "redirect:/departamentos/listar";
	}
	
	@RequestMapping("editar/{id}")
	public String find(@PathVariable("id") Integer id, ModelMap model) {
		model.addAttribute("departamento", service.find(id));
		
		return "/departamento/cadastro";
	}
	
	@RequestMapping(value="editar", method=RequestMethod.POST)
	public String editar(Departamento departamento, RedirectAttributes attr) {
		service.update(departamento);
		attr.addFlashAttribute("sucess", "Departamento editado com sucesso!");
		
		return "redirect:/departamentos/listar";
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