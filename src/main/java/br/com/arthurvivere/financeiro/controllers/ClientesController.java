package br.com.arthurvivere.financeiro.controllers;

import br.com.arthurvivere.financeiro.models.Clientes;
import br.com.arthurvivere.financeiro.models.dtos.ClienteDto;
import br.com.arthurvivere.financeiro.models.forms.AtualizaClienteForm;
import br.com.arthurvivere.financeiro.models.forms.ClienteForm;
import br.com.arthurvivere.financeiro.services.ClientesServices;
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
@RequestMapping("/clientes")
public class ClientesController {
    @Autowired
    private ClientesServices clientesServices;

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> get(@PathVariable int id) {
        Optional<Clientes> clientesOptional = clientesServices.findById(id);

        if(clientesOptional.isPresent()) {
            ClienteDto cliente = new ClienteDto(clientesOptional.get());

            return ResponseEntity.ok().body(cliente);
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<Page<ClienteDto>> getClientes(
            @RequestParam (required = false) String nome, String cpf_cnpj, String cidade, String uf,
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC,
            page = 0, size = 10) Pageable paginacao) {

        Page<Clientes> clientesResult = clientesServices.findByFilter(nome, cpf_cnpj, cidade, uf, paginacao);

        Page<ClienteDto> clientes = clientesResult.map(ClienteDto::new);

        return ResponseEntity.ok().body(clientes);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ClienteDto> create(@RequestBody @Valid ClienteForm clienteForm,
                                             UriComponentsBuilder uriBuilder) {
        try {
            Clientes cliente = clienteForm.converter();
            clientesServices.save(cliente);

            ClienteDto clienteDto = new ClienteDto(cliente);
            URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getId()).toUri();

            return ResponseEntity.created(uri).body(clienteDto);
        }catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<ClienteDto> remove(@PathVariable int id) {
        Optional<Clientes> clientesOptional = clientesServices.findById(id);

        if(clientesOptional.isPresent()) {
            clientesServices.deleteById(id);

            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<ClienteDto> update(@PathVariable int id, @RequestBody @Valid AtualizaClienteForm atualizaClienteForm) {
        Optional<Clientes> clientesOptional = clientesServices.findById(id);

        if(clientesOptional.isPresent()){
            Clientes cliente = clientesOptional.get();
            atualizaClienteForm.atualizar(cliente);

            return ResponseEntity.ok().body(new ClienteDto(cliente));
        }

        return ResponseEntity.notFound().build();
    }


}
