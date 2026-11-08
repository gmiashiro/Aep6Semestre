package br.com.sextoSementre.aep.controller;

import br.com.sextoSementre.aep.dto.ObservacaoRequest;
import br.com.sextoSementre.aep.dto.ObservacaoResponse;
import br.com.sextoSementre.aep.service.ObservacaoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/observacoes")
public class ObservacaoController {

    private final ObservacaoService service;

    public ObservacaoController(ObservacaoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ObservacaoResponse> criar(@Valid @RequestBody ObservacaoRequest request) {
        ObservacaoResponse response = service.criar(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(response.id()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    public List<ObservacaoResponse> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ObservacaoResponse buscar(@PathVariable String id) {
        return service.buscar(id);
    }

    @PutMapping("/{id}")
    public ObservacaoResponse atualizar(@PathVariable String id, @Valid @RequestBody ObservacaoRequest request) {
        return service.atualizar(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable String id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
