package com.digivox.controllers;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.digivox.models.Aluguel;
import com.digivox.repository.AluguelRepository;
import com.digivox.repository.ClienteRepository;
import com.digivox.repository.ItemRepository;

@Controller
@RequestMapping({ "/alugueis" })
public class AluguelController {

	@Autowired
	private AluguelRepository aluguelRepository;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private ItemRepository itemRepository;

	@GetMapping
	public ModelAndView index(String tipo) {
		ModelAndView mv = new ModelAndView("alugueis/index");
		Iterable<Aluguel> alugueis = aluguelRepository.findByTipo(tipo);
		mv.addObject("alugueis", alugueis);
		mv.addObject("tipo", tipo);
		mv.addObject("dateFormat", new SimpleDateFormat("dd/MM/yyyy"));
		mv.addObject("numberFormat", NumberFormat.getCurrencyInstance(new Locale("pt", "BR")));
		return mv;
	}

	@GetMapping(path = { "/reservar" })
	public ModelAndView reservar() {
		ModelAndView mv = new ModelAndView("alugueis/reservar");
		mv.addObject("Aluguel", new Aluguel());
		mv.addObject("itens", itemRepository.findAll());
		mv.addObject("clientes", clienteRepository.findAll());
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		c.add(Calendar.DATE, 1);
		Date amanha = c.getTime();
		mv.addObject("amanha", dateFormat.format(amanha));
		return mv;
	}

	@GetMapping(path = { "/alugar" })
	public ModelAndView alugar() {
		ModelAndView mv = new ModelAndView("alugueis/alugar");
		mv.addObject("Aluguel", new Aluguel());
		mv.addObject("itens", itemRepository.findAll());
		mv.addObject("clientes", clienteRepository.findAll());
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date hoje = new Date();
		mv.addObject("hoje", dateFormat.format(hoje));
		return mv;
	}

	@PostMapping
	public String store(@Valid Aluguel aluguel, RedirectAttributes attributes) {
		Collection<Aluguel> list = (Collection<Aluguel>) aluguelRepository.findItemByPeriodo(aluguel.getItem().getId(),
				aluguel.getInicio(), aluguel.getFim());
		if (aluguel.getTipo().equals("Aluguel")) {
			System.out.println("Aluguel");
			if (list.size() > 0) {
				attributes.addFlashAttribute("mensagem", "Este item já está alugado no período selecionado!");
				return "redirect:/alugueis/alugar";
			} else {
				aluguelRepository.save(aluguel);
				return "redirect:/alugueis?tipo=" + aluguel.getTipo();
			}

		} else {
			System.out.println("Reserva");
			if (list.size() > 0) {
				attributes.addFlashAttribute("mensagem", "Este item já está reservado no período selecionado!");
				return "redirect:/alugueis/reservar";
			} else {
				aluguelRepository.save(aluguel);
				return "redirect:/alugueis?tipo=" + aluguel.getTipo();
			}
		}
	}

	@GetMapping(path = { "/{id}/delete" })
	public String delete(@PathVariable("id") long id) {
		Aluguel aluguel = aluguelRepository.findById(id);
		aluguelRepository.delete(aluguel);
		return "redirect:/alugueis?tipo=" + aluguel.getTipo();
	}
}
