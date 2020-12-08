package br.com.arthurvivere.financeiro.controllers;

import br.com.arthurvivere.financeiro.models.Clientes;
import br.com.arthurvivere.financeiro.models.LivroCaixa;
import br.com.arthurvivere.financeiro.models.dtos.ContabeisDto;
import br.com.arthurvivere.financeiro.services.ClientesServices;
import br.com.arthurvivere.financeiro.services.LivroCaixaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contabeis")
public class ContabeisController {
    @Autowired
    private LivroCaixaServices livroCaixaServices;
    @Autowired
    private ClientesServices clientesServices;

    @GetMapping("/{id}")
    public ResponseEntity<ContabeisDto> get(@PathVariable int id, @RequestParam Date dataInicial, Date dataFinal) {
        Optional<Clientes> clientesOptional = clientesServices.findById(id);

        if(clientesOptional.isPresent()){
            Clientes cliente = clientesOptional.get();
            Optional<List<LivroCaixa>> livrosCaixaOptional = livroCaixaServices.findByDataLimite(cliente, dataInicial, dataFinal);
            if(livrosCaixaOptional.isPresent()){
                List<LivroCaixa> livrosCaixa = livrosCaixaOptional.get();
                ContabeisDto contabeisDto = new ContabeisDto(livrosCaixa);
                return ResponseEntity.ok().body(contabeisDto);
            }
        }

        return ResponseEntity.notFound().build();
    }
}
