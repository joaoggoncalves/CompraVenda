package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import br.ufscar.dc.dsw.domain.Proposta;


public interface IPropostaDAO extends CrudRepository<Proposta, Long> {
    /*@Query("UPDATE Proposta SET statusproposta = ACEITO WHERE id = :id")
    public void aceitaProposta(@Param("id") Long id);
    @Query("UPDATE Proposta SET statusproposta = NAO ACEITO WHERE id = :id")
    public void recusaProposta(@Param("id") Long id);*/
    List<Proposta> findAll();
    Proposta save(Proposta proposta);
    Proposta findById(long id);
    void deleteById(Long id);
}
