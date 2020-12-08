package br.com.arthurvivere.financeiro.services;

import br.com.arthurvivere.financeiro.models.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface UsuarioServices extends JpaRepository<Usuario, Integer> {
    @Query("select u from Usuario u where"  + "(:nome is null or u.nome like :nome)" + "and"
            + "(:email is null or u.email like :email)")
    Page<Usuario> findByFilter(@Param("nome") String nome, @Param("email") String email, Pageable paginacao);
    Optional<Usuario> findByLogin(String login);
}
