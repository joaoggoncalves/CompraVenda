package br.ufscar.dc.dsw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.dao.ICarroDAO;
import br.ufscar.dc.dsw.domain.Carro;
import br.ufscar.dc.dsw.service.spec.ICarroService;

@Service
@Transactional(readOnly = false)
public class CarroService implements ICarroService {

    @Autowired
    ICarroDAO dao;

    public Carro buscarPorPlaca(String placa) {
        return dao.getCarroByPlaca(placa);
    }

    @Transactional(readOnly = true)
    public List<Carro> todosCarros() {
        return dao.findAll();
    }

    public void salvar(Carro carro) {
        dao.save(carro);
    }

    public void excluirPorId(Long id) {
        dao.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Carro buscarPorId(Long id) {
        return dao.findById(id.longValue());
    }

}
