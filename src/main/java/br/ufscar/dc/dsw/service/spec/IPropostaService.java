package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Proposta;

public interface IPropostaService {
    //void aceitarProposta(Long id); 
    //void recusarProposta(Long id); 
    List<Proposta> todasPropostas();
    void salvar(Proposta proposta);
    void excluirPorId(Long id);
    Proposta buscarPorId(Long id);
}