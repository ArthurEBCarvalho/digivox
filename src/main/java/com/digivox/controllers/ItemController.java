package com.digivox.controllers;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.digivox.models.Item;
import com.digivox.repository.ItemRepository;
import com.digivox.repository.TipoItemRepository;

@Controller
@RequestMapping({ "/itens" })
public class ItemController {
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private TipoItemRepository tipoItemRepository;

	@GetMapping
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("itens/index");
		Iterable<Item> itens = itemRepository.findAll();
		mv.addObject("itens", itens);
		return mv;
	}

	@GetMapping(path = { "/create" })
	public ModelAndView create() {
		ModelAndView mv = new ModelAndView("itens/form");
		mv.addObject("item", new Item());
		mv.addObject("tipoItens", tipoItemRepository.findAll());
		mv.addObject("method", "post");
		return mv;
	}

	@PostMapping
	public String store(@Valid Item item) {
		itemRepository.save(item);
		return "redirect:/itens";
	}

	@GetMapping(path = { "/{id}/edit" })
	public ModelAndView edit(@PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView("itens/form");
		mv.addObject("item", itemRepository.findById(id));
		mv.addObject("tipoItens", tipoItemRepository.findAll());
		mv.addObject("method", "put");
		mv.addObject("dateFormat", new SimpleDateFormat("dd/MM/yyyy"));
		mv.addObject("numberFormat", NumberFormat.getCurrencyInstance(new Locale("pt", "BR")));
		return mv;
	}

	@PutMapping(value = "/{id}")
	public String update(@PathVariable("id") long id, @Valid Item item) {
		Item reg = itemRepository.findById(id);
		reg.setNome(item.getNome());
		itemRepository.save(reg);
		return "redirect:/itens";
	}

	@GetMapping(path = { "/{id}/delete" })
	public String delete(@PathVariable("id") long id) {
		Item item = itemRepository.findById(id);
		itemRepository.delete(item);
		return "redirect:/itens";
	}
}
