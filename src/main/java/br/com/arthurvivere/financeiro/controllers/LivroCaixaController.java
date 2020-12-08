package br.com.arthurvivere.financeiro.controllers;

import br.com.arthurvivere.financeiro.models.Clientes;
import br.com.arthurvivere.financeiro.models.LivroCaixa;
import br.com.arthurvivere.financeiro.models.dtos.LivroCaixaDto;
import br.com.arthurvivere.financeiro.models.forms.AtualizaLivroCaixaForm;
import br.com.arthurvivere.financeiro.models.forms.LivroCaixaForm;
import br.com.arthurvivere.financeiro.services.ClientesServices;
import br.com.arthurvivere.financeiro.services.LivroCaixaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Optional;


@RestController
@RequestMapping("/livro-caixa")
public class LivroCaixaController {
    @Autowired
    private LivroCaixaServices livroCaixaService;
    @Autowired
    private ClientesServices clientesServices;

    @GetMapping("/{id}")
    public ResponseEntity<LivroCaixaDto> get(@PathVariable int id) {
        Optional<LivroCaixa> livroCaixaOptional = livroCaixaService.findById(id);

        if(livroCaixaOptional.isPresent()) {
            LivroCaixa livroCaixa = livroCaixaOptional.get();

            return ResponseEntity.ok().body(new LivroCaixaDto(livroCaixa));
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<Page<LivroCaixaDto>> getLivrosCaixa(@RequestParam int cliente_id, Pageable paginacao) {
        Optional<Clientes> clientesOptional =  clientesServices.findById(cliente_id);

        if(clientesOptional.isPresent()) {
            Clientes cliente = clientesOptional.get();
            Page<LivroCaixa> livroCaixas = livroCaixaService.findByCliente(cliente, paginacao);

            Page<LivroCaixaDto> livroCaixaDtos = livroCaixas.map(LivroCaixaDto::new);
            return ResponseEntity.ok().body(livroCaixaDtos);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable int id) {
        Optional<LivroCaixa> livroCaixaOptional = livroCaixaService.findById(id);

        if(livroCaixaOptional.isPresent()) {
            livroCaixaService.deleteById(id);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroCaixaDto> update(@PathVariable int id, @RequestBody @Valid AtualizaLivroCaixaForm attLivroCaixa) {
        Optional<LivroCaixa> livroCaixaOptional = livroCaixaService.findById(id);

        if(livroCaixaOptional.isPresent()) {
            LivroCaixa livroCaixa = livroCaixaOptional.get();

            attLivroCaixa.atualizar(livroCaixa);

            return ResponseEntity.ok().body(new LivroCaixaDto(livroCaixa));
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<LivroCaixaDto> create(@RequestBody @Valid LivroCaixaForm livroCaixaForm) {
        Optional<Clientes> clienteOptional = clientesServices.findById(livroCaixaForm.getCliente_id());
        Optional<LivroCaixa> ultimoLivroCaixaOptional = livroCaixaService.findTopByOrderByIdDesc();

        BigDecimal saldo;

        if(ultimoLivroCaixaOptional.isPresent()) {
            LivroCaixa ultimoLivroCaixa = ultimoLivroCaixaOptional.get();

            saldo = (livroCaixaForm.getTipo() == 'C')
                    ? ultimoLivroCaixa.getSaldo().add(livroCaixaForm.getValor())
                    : ultimoLivroCaixa.getSaldo().subtract(livroCaixaForm.getValor());
        } else {
            saldo = (livroCaixaForm.getTipo() == 'C')
                    ? livroCaixaForm.getValor()
                    : new BigDecimal("0.0").subtract(livroCaixaForm.getValor());
        }

        if(clienteOptional.isPresent()) {
            Clientes cliente = clienteOptional.get();
            LivroCaixa livroCaixa = livroCaixaForm.converter(cliente, saldo);
            livroCaixaService.save(livroCaixa);

            LivroCaixaDto livroCaixaDto = new LivroCaixaDto(livroCaixa);

            return ResponseEntity.ok().body(livroCaixaDto);
        }

        return ResponseEntity.notFound().build();
    }
}
