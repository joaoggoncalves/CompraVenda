package br.ufscar.dc.dsw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.service.spec.IUsuarioService;
import br.ufscar.dc.dsw.security.UsuarioDetails;

@Controller
@RequestMapping("/user")
public class UsuarioController {
    @Autowired
	private IUsuarioService service;
	

    @GetMapping("/userindex")
    public String userindex() {
        return "user/userindex";
    }

    @GetMapping(path = {"/cadastroproposta", "/cadastroproposta/{id}"})
    public String cadastroproposta(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("idcarro", id);
        return "user/cadastroproposta";
    }

    @ModelAttribute("usuarioatual")
    public Usuario getUserAtual() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ((UsuarioDetails)principal).getUsuario();
    }
}