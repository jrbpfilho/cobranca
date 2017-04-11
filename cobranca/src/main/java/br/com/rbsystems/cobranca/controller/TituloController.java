package br.com.rbsystems.cobranca.controller;


import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.rbsystems.cobranca.model.StatusTitulo;
import br.com.rbsystems.cobranca.model.Titulo;
import br.com.rbsystems.cobranca.repository.Titulos;



@Controller
@RequestMapping("/titulos")
public class TituloController {
	
	private static final String CADASTRO_VIEW = "CadastroTitulo";
	
	@Autowired
	private Titulos titulos;
	
	
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Titulo());
		mv.addObject("todosStatusTitulo", StatusTitulo.values());
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST)
	/*
	 * Código anterior para redirect
	 * public ModelAndView salvar(@Validated Titulo titulo, Errors errors) {
	 */
	
	public String salvar(@Validated Titulo titulo, Errors errors, RedirectAttributes attributes) {
		
		/* Código anterior para redirect
		
		ModelAndView mv = new ModelAndView("CadastroTitulo");
		
		if(errors.hasErrors()) {
			return mv;
		}
		
		*/
		
		if(errors.hasErrors()) {
			return "CadastroTitulo";
		}
		
		System.out.println(">>> " + titulo.getDescricao());
		titulos.save(titulo);
		attributes.addFlashAttribute("mensagem", "Título salvo com sucesso!!!!");
		return "redirect:/titulos/novo";
	}
	
	@RequestMapping
	public ModelAndView pesquisar() {
		List<Titulo> todosTitulos = titulos.findAll();
		ModelAndView mv = new ModelAndView("PesquisaTitulos");
		mv.addObject("titulos", todosTitulos);
		return mv;
	}
	
	@RequestMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") Titulo titulo) {
		
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(titulo);
		return mv;
		
	}
	
	@RequestMapping(value="{id}" , method = RequestMethod.DELETE)
	public void excluir(@PathVariable Long id) {
		
		titulos.delete(id);
		
		return "redirect:/titulos";
		
	}
		
	@ModelAttribute("todosStatusTitulo")
	public List<StatusTitulo> todosStatusTitulo() {
		return Arrays.asList(StatusTitulo.values());
	}
}
 