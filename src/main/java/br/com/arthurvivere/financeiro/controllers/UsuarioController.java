package br.com.arthurvivere.financeiro.controllers;

import br.com.arthurvivere.financeiro.models.Usuario;
import br.com.arthurvivere.financeiro.models.dtos.UsuarioDto;
import br.com.arthurvivere.financeiro.models.forms.AtualizaUsuarioForm;
import br.com.arthurvivere.financeiro.models.forms.UsuarioForm;
import br.com.arthurvivere.financeiro.services.UsuarioServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioServices usuarioServices;

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> get(@PathVariable int id) {
        Optional<Usuario> optionalUsuario = usuarioServices.findById(id);

        if(optionalUsuario.isPresent()) {
            return ResponseEntity.ok().body(new UsuarioDto(optionalUsuario.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<Page<UsuarioDto>> getUsuarios(@RequestParam(required = false) String nome, String email,
                                                        @PageableDefault(sort = "id", direction = Sort.Direction.ASC,
                                                        page = 0, size = 10) Pageable paginacao) {
        Page<Usuario> usuarios = usuarioServices.findByFilter(nome, email, paginacao);

        Page<UsuarioDto> usuarioDtos = usuarios.map(UsuarioDto::new);

        return ResponseEntity.ok().body(usuarioDtos);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<UsuarioDto> create(@RequestBody @Valid UsuarioForm form,
                                                 UriComponentsBuilder uriBuilder) {
        try{
            Usuario usuario = form.converter();

            usuarioServices.save(usuario);

            URI uri = uriBuilder.path("/usuario/{id}").buildAndExpand(usuario.getId()).toUri();

            return ResponseEntity.created(uri).body(new UsuarioDto(usuario));
        } catch(Exception ex) {
            System.out.println("=============================" + ex.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remove(@PathVariable int id) {
        Optional<Usuario> usuarioOptional = usuarioServices.findById(id);

        if(usuarioOptional.isPresent()) {
            usuarioServices.deleteById(id);

            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<UsuarioDto> update(@PathVariable int id, @RequestBody @Valid
            AtualizaUsuarioForm atualizaUsuarioForm) {
        Optional<Usuario> usuarioOptional = usuarioServices.findById(id);

        if(usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            atualizaUsuarioForm.atualizar(usuario);

            return ResponseEntity.ok().body(new UsuarioDto(usuario));
        }

        return ResponseEntity.notFound().build();
    }
}
