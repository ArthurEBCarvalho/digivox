package com.digivox.controllers;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.digivox.repository.AluguelRepository;

@Controller
public class IndexController {

	@Autowired
	private AluguelRepository aluguelRepository;

	@RequestMapping("/")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("index");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.clear(Calendar.MINUTE);
		cal.clear(Calendar.SECOND);
		cal.clear(Calendar.MILLISECOND);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		Date dataInicio = cal.getTime();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		Date dataFim = cal.getTime();
		mv.addObject("devolvidos", aluguelRepository.findByFim(dataInicio, dataFim));
		mv.addObject("alugados", aluguelRepository.findByInicio(dataInicio, dataFim));
		mv.addObject("reservados", aluguelRepository.findByTipo("Reserva"));
		mv.addObject("dateFormat", new SimpleDateFormat("dd/MM/yyyy"));
		mv.addObject("numberFormat", NumberFormat.getCurrencyInstance(new Locale("pt", "BR")));
		return mv;
	}
}
