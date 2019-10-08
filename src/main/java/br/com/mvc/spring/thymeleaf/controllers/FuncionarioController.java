package br.com.mvc.spring.thymeleaf.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
}
