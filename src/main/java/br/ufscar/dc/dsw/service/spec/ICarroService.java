package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Carro;

public interface ICarroService {
    Carro buscarPorPlaca(String placa);
    List<Carro> todosCarros();
    void salvar(Carro carro);
    void excluirPorPlaca(String placa);
}
