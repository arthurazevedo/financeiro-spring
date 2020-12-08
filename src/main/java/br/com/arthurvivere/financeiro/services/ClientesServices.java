package br.com.arthurvivere.financeiro.services;

import br.com.arthurvivere.financeiro.models.Clientes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClientesServices extends JpaRepository<Clientes, Integer> {
    @Query("select c from Clientes c where (:nome is null or c.nome like :nome) and (:cpf_cnpj is null or " +
            "c.cpf_cnpj like :cpf_cnpj) and (:cidade is null or c.cidade like :cidade) and (:uf is null or c.uf like :uf)")
    Page<Clientes> findByFilter(@Param("nome") String nome,@Param("cpf_cnpj") String cpf_cnpj, @Param("cidade") String cidade,
    @Param("uf") String uf, Pageable paginacao);
}
