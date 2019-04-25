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

import com.digivox.models.Cliente;
import com.digivox.repository.ClienteRepository;

@Controller
@RequestMapping({ "/clientes" })
public class ClienteController {
	@Autowired
	private ClienteRepository clienteRepository;

	@GetMapping
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("clientes/index");
		Iterable<Cliente> clientes = clienteRepository.findAll();
		mv.addObject("clientes", clientes);
		return mv;
	}

	@GetMapping(path = { "/create" })
	public ModelAndView create() {
		ModelAndView mv = new ModelAndView("clientes/form");
		mv.addObject("cliente", new Cliente());
		mv.addObject("method", "post");
		return mv;
	}

	@PostMapping
	public String store(@Valid Cliente Cliente) {
		clienteRepository.save(Cliente);
		return "redirect:/clientes";
	}

	@GetMapping(path = { "/{id}/edit" })
	public ModelAndView edit(@PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView("clientes/form");
		mv.addObject("cliente", clienteRepository.findById(id));
		mv.addObject("method", "put");
		mv.addObject("dateFormat", new SimpleDateFormat("dd/MM/yyyy"));
		mv.addObject("numberFormat", NumberFormat.getCurrencyInstance(new Locale("pt", "BR")));
		return mv;
	}

	@PutMapping(value = "/{id}")
	public String update(@PathVariable("id") long id, @Valid Cliente cliente) {
		Cliente reg = clienteRepository.findById(id);
		reg.setNome(cliente.getNome());
		clienteRepository.save(reg);
		return "redirect:/clientes";
	}

	@GetMapping(path = { "/{id}/delete" })
	public String delete(@PathVariable("id") long id) {
		Cliente cliente = clienteRepository.findById(id);
		clienteRepository.delete(cliente);
		return "redirect:/clientes";
	}
}
