package br.com.sextoSementre.aep.controller;

import br.com.sextoSementre.aep.dto.LocalObservacaoRequest;
import br.com.sextoSementre.aep.dto.LocalObservacaoResponse;
import br.com.sextoSementre.aep.service.LocalObservacaoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/locais-observacao")
public class LocalObservacaoController {

    private final LocalObservacaoService service;

    public LocalObservacaoController(LocalObservacaoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<LocalObservacaoResponse> criar(@Valid @RequestBody LocalObservacaoRequest request) {
        LocalObservacaoResponse response = service.criar(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    public List<LocalObservacaoResponse> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public LocalObservacaoResponse buscar(@PathVariable String id) {
        return service.buscar(id);
    }

    @PutMapping("/{id}")
    public LocalObservacaoResponse atualizar(@PathVariable String id, @Valid @RequestBody LocalObservacaoRequest request) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable String id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
