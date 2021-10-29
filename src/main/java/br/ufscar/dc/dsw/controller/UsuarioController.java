package br.ufscar.dc.dsw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufscar.dc.dsw.domain.Proposta;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.service.spec.ICarroService;
import br.ufscar.dc.dsw.service.spec.IPropostaService;
import br.ufscar.dc.dsw.security.UsuarioDetails;

@Controller
@RequestMapping("/user")
public class UsuarioController {
    @Autowired
	private IPropostaService service;

    @Autowired
    private ICarroService servicecarro;
	

    @GetMapping("/userindex")
    public String userindex() {
        return "user/userindex";
    }

    @GetMapping("/cadastroproposta/{id}")
    public String cadastroproposta(@PathVariable("id") Long id, ModelMap model, Proposta proposta) {
        model.addAttribute("carroatual", servicecarro.buscarPorId(id));
        return "user/cadastroproposta";
    }

    @ModelAttribute("usuarioatual")
    public Usuario getUserAtual() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ((UsuarioDetails)principal).getUsuario();
    }
}