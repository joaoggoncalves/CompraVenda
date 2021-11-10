package br.ufscar.dc.dsw.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Carro;
import br.ufscar.dc.dsw.domain.Proposta;
import br.ufscar.dc.dsw.domain.Usuario;
import br.ufscar.dc.dsw.service.spec.ICarroService;
import br.ufscar.dc.dsw.service.spec.IPropostaService;
import br.ufscar.dc.dsw.service.spec.IUsuarioService;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
	private IUsuarioService service;

    @Autowired
    private ICarroService carroservice;

    @Autowired
    private IPropostaService propostaservice;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

    @GetMapping("/adminindex")
    public String adminindex() {
        return "admin/adminindex";
    }

    @GetMapping("/listausers")
    public String lista(ModelMap model) {
        List<Usuario> lista = service.buscarTodos();
        List<Usuario> listafiltrada = new ArrayList<Usuario>();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getRole().contains("ROLE_USER")) {
                listafiltrada.add(lista.get(i));
            }
        }
        model.addAttribute("usuarios", listafiltrada);
        return "admin/listausers";
    }

    @GetMapping("/listalojas")
    public String listalojas(ModelMap model) {
        List<Usuario> lista = service.buscarTodos();
        List<Usuario> listafiltrada = new ArrayList<Usuario>();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getRole().contains("ROLE_LOJA")) {
                listafiltrada.add(lista.get(i));
            }
        }
        model.addAttribute("usuarios", listafiltrada);
        return "admin/listalojas";
    }

    @GetMapping("/cadastrouser")
    public String cadastro(Usuario usuario) {
        return "admin/cadastrouser";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Usuario usuario, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "admin/cadastrouser";
        }
        usuario.setPassword(encoder.encode(usuario.getPassword()));
		service.salvar(usuario);
        attr.addFlashAttribute("success", "Usuário inserido com sucesso.");
        return "redirect:/admin/adminindex";
    }

    @GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("usuario", service.buscarPorId(id));
		return "admin/cadastrouser";
	}

    @PostMapping("/editar")
	public String editar(@Valid Usuario usuario, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return "admin/cadastrousuario";
		}
		
		service.salvar(usuario);
		attr.addFlashAttribute("success", "Usuário editado com sucesso.");
		return "redirect:/admin/adminindex";
	}

    @GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
        Usuario excluido = service.buscarPorId(id);
        if (excluido.getRole().equals("ROLE_LOJA")) {
            //Apagar carros da loja e propostas antes da exclusão
            List<Carro> listacarros = carroservice.todosCarros();
            List<Carro> carrosdaloja = new ArrayList<Carro>();

            List<Proposta> listapropostas = propostaservice.todasPropostas();

            for (int i = 0; i < listacarros.size(); i++) {
                if (listacarros.get(i).getUsuario().getId() == excluido.getId()) {
                    carrosdaloja.add(listacarros.get(i));
                }
            }

            for (int k = 0; k < listapropostas.size(); k++) {
                for (int m = 0; m < carrosdaloja.size(); m++) {
                    if (listapropostas.get(k).getCarro().getId() == carrosdaloja.get(m).getId()) {
                        propostaservice.excluirPorId(listapropostas.get(k).getId());
                        listapropostas.remove(k);
                    }
                }
            }

            for (int j = 0; j < carrosdaloja.size(); j++) {
                carroservice.excluirPorId(carrosdaloja.get(j).getId());
            }
        }
		service.excluir(id);
		model.addAttribute("sucess", "Usuário excluído com sucesso.");
		return adminindex();
	}
}