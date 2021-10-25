package br.ufscar.dc.dsw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufscar.dc.dsw.service.spec.IUsuarioService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
	private IUsuarioService service;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

    @GetMapping("/adminindex")
    public String home() {
        return "admin/adminindex";
    }
}