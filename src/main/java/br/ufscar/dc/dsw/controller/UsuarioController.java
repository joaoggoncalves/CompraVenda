package br.ufscar.dc.dsw.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/listapropostauser")
    public String listapropostauser(ModelMap model) {
        List<Proposta> listapropostas = service.todasPropostas();
        List<Proposta> listapropostafiltrada = new ArrayList<Proposta>();
        Object atual = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long idatual = ((UsuarioDetails)atual).getUsuario().getId();
        for (int i = 0; i < listapropostas.size(); i++) {
            if (listapropostas.get(i).getUsuario().getId() == idatual) {
                listapropostafiltrada.add(listapropostas.get(i));
            }
        }
        model.addAttribute("propostas", listapropostafiltrada);
        return "user/listapropostauser";
    }

    @PostMapping("/salvarproposta")
    public String salvarProposta(@Valid Proposta proposta, BindingResult result, RedirectAttributes attr, ModelMap model) {
        if (result.hasErrors()) {
            return "user/cadastroproposta";
        }

        List<Proposta> listapropostas = service.todasPropostas();
        for (int i = 0; i < listapropostas.size(); i++) {
            if (listapropostas.get(i).getCarro().getId() == proposta.getCarro().getId()) {
                result.addError(new ObjectError("Proposta", "Você já possui uma proposta neste carro"));
                Long id = proposta.getCarro().getId();
                model.addAttribute("repetida", 1);
                return cadastroproposta(id, model, proposta);
            }
        }

        service.salvar(proposta);
        model.addAttribute("repetida", 0);
        attr.addFlashAttribute("success", "Proposta inserida com sucesso.");
        return "redirect:/user/userindex";
    }

    @ModelAttribute("usuarioatual")
    public Usuario getUserAtual() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ((UsuarioDetails)principal).getUsuario();
    }
}