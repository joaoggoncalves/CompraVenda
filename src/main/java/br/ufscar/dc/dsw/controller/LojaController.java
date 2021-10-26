package br.ufscar.dc.dsw.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Carro;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.security.UsuarioDetails;
import br.ufscar.dc.dsw.service.spec.ICarroService;

@Controller
@RequestMapping("/loja")
public class LojaController {
    @Autowired
	private ICarroService carroservice;

    @GetMapping("/lojaindex")
    public String home(ModelMap model) {
        List<Carro> listacarros = carroservice.todosCarros();
        List<Carro> listacarrosfiltrada = new ArrayList<Carro>();
        Object atual = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long idatual = ((UsuarioDetails)atual).getUsuario().getId();
        for (int i = 0; i < listacarros.size(); i++) {
            if (listacarros.get(i).getUsuario().getId() == idatual) {
                listacarrosfiltrada.add(listacarros.get(i));
            }
        }
        model.addAttribute("carros", listacarrosfiltrada);
        return "loja/lojaindex";
    }

    @GetMapping("/cadastrocarro")
    public String cadastrocarro(Carro carro, ModelMap model) {
        return "loja/cadastrocarro";
    }

    @PostMapping("/salvarcarro")
    public String salvarcarro(@Valid Carro carro, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
			return "loja/cadastrocarro";
		}

        carroservice.salvar(carro);
        attr.addFlashAttribute("success", "Carro inserido com sucesso.");
        return "redirect:/loja/lojaindex";
    }

    @ModelAttribute("usuarioatual")
    public Usuario getUserAtual() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ((UsuarioDetails)principal).getUsuario();
    }
}