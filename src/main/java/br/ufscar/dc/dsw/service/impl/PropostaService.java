package br.ufscar.dc.dsw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.dao.IPropostaDAO;
import br.ufscar.dc.dsw.domain.Proposta;
import br.ufscar.dc.dsw.service.spec.IPropostaService;

@Service
@Transactional(readOnly = false)
public class PropostaService implements IPropostaService {

    @Autowired
    IPropostaDAO dao;

    @Transactional
    @Modifying
    public void aceitarProposta(Long id) {
        dao.aceitaProposta(id);
    }

    @Transactional
    @Modifying
    public void recusarProposta(Long id) {
        dao.recusaProposta(id);
    }

    @Transactional(readOnly = true)
    public List<Proposta> todasPropostas() {
        return dao.findAll();
    }

    public void salvar(Proposta proposta) {
        dao.save(proposta);
    }

    public void excluirPorId(Long id) {
        dao.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Proposta buscarPorId(Long id) {
        return dao.findById(id.longValue());
    }
}
