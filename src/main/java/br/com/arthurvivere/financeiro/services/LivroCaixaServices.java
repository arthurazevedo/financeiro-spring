package br.com.arthurvivere.financeiro.services;

import br.com.arthurvivere.financeiro.models.Clientes;
import br.com.arthurvivere.financeiro.models.LivroCaixa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface LivroCaixaServices extends JpaRepository<LivroCaixa, Integer> {
    Optional<LivroCaixa> findTopByOrderByIdDesc();
    Page<LivroCaixa> findByCliente(Clientes cliente, Pageable pageable);
    @Query("SELECT l FROM LivroCaixa l WHERE l.cliente = :cliente AND l.dataLancamento BETWEEN :dataInicial and :dataFinal")
    Optional<List<LivroCaixa>> findByDataLimite(@Param("cliente") Clientes cliente, @Param("dataInicial") Date dataInicial, @Param("dataFinal") Date dataFinal);
}
