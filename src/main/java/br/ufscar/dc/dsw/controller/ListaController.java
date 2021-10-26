package br.ufscar.dc.dsw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufscar.dc.dsw.service.spec.ICarroService;

@Controller
@RequestMapping("/listacarros")
public class ListaController {

    @Autowired
	private ICarroService service;

    @GetMapping("/lista")
    public String listacarros(ModelMap model) {
        model.addAttribute("carros", service.todosCarros());
        return "/lista";
    }
}
