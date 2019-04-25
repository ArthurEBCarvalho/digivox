package com.digivox.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.digivox.models.TipoItem;
import com.digivox.repository.TipoItemRepository;

@Controller
@RequestMapping({ "/tipoItens" })
public class TipoItemController {

	@Autowired
	private TipoItemRepository tipoItemRepository;

	@GetMapping
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("tipoItens/index");
		Iterable<TipoItem> tipoItens = tipoItemRepository.findAll();
		mv.addObject("tipoItens", tipoItens);
		return mv;
	}

	@GetMapping(path = { "/create" })
	public ModelAndView create() {
		ModelAndView mv = new ModelAndView("tipoItens/form");
		mv.addObject("tipoItem", new TipoItem());
		mv.addObject("method", "post");
		return mv;
	}

	@PostMapping
	public String store(@Valid TipoItem tipoItem) {
		tipoItemRepository.save(tipoItem);
		return "redirect:/tipoItens";
	}

	@GetMapping(path = { "/{id}/edit" })
	public ModelAndView edit(@PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView("tipoItens/form");
		mv.addObject("tipoItem", tipoItemRepository.findById(id));
		mv.addObject("method", "put");
		return mv;
	}

	@PutMapping(value = "/{id}")
	public String update(@PathVariable("id") long id, @Valid TipoItem tipoItem) {
		TipoItem reg = tipoItemRepository.findById(id);
		reg.setNome(tipoItem.getNome());
		tipoItemRepository.save(reg);
		return "redirect:/tipoItens";
	}

	@GetMapping(path = { "/{id}/delete" })
	public String delete(@PathVariable("id") long id) {
		TipoItem tipoItem = tipoItemRepository.findById(id);
		tipoItemRepository.delete(tipoItem);
		return "redirect:/tipoItens";
	}
}
