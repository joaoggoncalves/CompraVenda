package br.ufscar.dc.dsw.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Carro;
import br.ufscar.dc.dsw.domain.Proposta;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.security.UsuarioDetails;
import br.ufscar.dc.dsw.service.spec.ICarroService;
import br.ufscar.dc.dsw.service.spec.IPropostaService;

@Controller
@RequestMapping("/loja")
public class LojaController {
    @Autowired
	private ICarroService carroservice;

    @Autowired
	private IPropostaService service;

    @GetMapping("/lojaindex")
    public String lojaindex(ModelMap model) {
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

    @GetMapping("/listapropostaloja")
    public String listapropostaloja(ModelMap model) {
        List<Carro> listacarros = carroservice.todosCarros();
        List<Carro> listacarrosfiltrada = new ArrayList<Carro>();
        Object atual = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long idatual = ((UsuarioDetails)atual).getUsuario().getId();
        for (int i = 0; i < listacarros.size(); i++) {
            if (listacarros.get(i).getUsuario().getId() == idatual) {
                listacarrosfiltrada.add(listacarros.get(i));
            }
        }

        List<Proposta> listapropostas = service.todasPropostas();
        List<Proposta> listapropostafiltrada = new ArrayList<Proposta>();
        for (int i = 0; i < listapropostas.size(); i++) {
            if (listacarrosfiltrada.contains(listapropostas.get(i).getCarro())) {
                listapropostafiltrada.add(listapropostas.get(i));
            }
        }
        model.addAttribute("propostas", listapropostafiltrada);
        return "loja/listapropostaloja";
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

    @GetMapping("/editarcarro/{id}")
	public String preEditarCarro(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("carro", carroservice.buscarPorId(id));
		return "loja/cadastrocarro";
	}

    @PostMapping("/editarcarro")
    public String editarCarro(@Valid Carro carro, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
			return "loja/cadastrocarro";
		}
        carroservice.salvar(carro);
        attr.addFlashAttribute("success", "Carro editado com sucesso.");
        return "redirect:/loja/lojaindex";
    }

    @GetMapping("/removercarro/{id}")
	public String excluirCarro(@PathVariable("id") Long id, ModelMap model) {
		carroservice.excluirPorId(id);
		model.addAttribute("sucess", "Usuário excluído com sucesso.");
		return lojaindex(model);
	}

    @GetMapping("/aceitar/{id}")
    public String aceitarProposta(@PathVariable("id") Long id, ModelMap model) {
        service.aceitarProposta(id);
        model.addAttribute("sucess", "Proposta aceita.");
        return lojaindex(model);
    }

    @GetMapping("/recusar/{id}")
    public String recusarProposta(@PathVariable("id") Long id, ModelMap model) {
        service.recusarProposta(id);
        model.addAttribute("sucess", "Proposta recusada.");
        return lojaindex(model);
    }

    @ModelAttribute("usuarioatual")
    public Usuario getUserAtual() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ((UsuarioDetails)principal).getUsuario();
    }
}