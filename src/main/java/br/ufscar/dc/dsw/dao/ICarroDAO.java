package br.ufscar.dc.dsw.dao;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.CrudRepository;


import br.ufscar.dc.dsw.domain.Carro;

@SuppressWarnings("unchecked")
public interface ICarroDAO extends CrudRepository<Carro, Long> {
    @Query("SELECT c FROM Carro c WHERE c.placa = :placa")
    public Carro getCarroByPlaca(@Param("placa") String placa);
    List<Carro> findAll();
    Carro save(Carro carro);
    @Query("DELETE FROM Carro WHERE placa = ?1")
    void deleteByPlaca(String placa);
}
