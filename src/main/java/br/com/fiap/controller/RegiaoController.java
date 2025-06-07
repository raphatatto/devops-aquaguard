package br.com.fiap.controller;

import br.com.fiap.dto.RegiaoDTO;
import br.com.fiap.dto.RegiaoForm;
import br.com.fiap.service.RegiaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/regioes")
public class RegiaoController {

    @Autowired
    private RegiaoService service;

    @GetMapping
    public Page<RegiaoDTO> listar(
            @PageableDefault(size = 10, sort = "id") Pageable pageable,
            @RequestParam Optional<String> cidade
    ) {
        return service.listar(pageable, cidade);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegiaoDTO> getById(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RegiaoDTO> create(@Valid @RequestBody RegiaoForm form) {
        RegiaoDTO dto = service.criar(form);
        return ResponseEntity
                .created(URI.create("/api/regioes/" + dto.id()))
                .body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegiaoDTO> update(@PathVariable Long id,
                                            @Valid @RequestBody RegiaoForm form) {
        return service.atualizar(id, form)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (service.deletar(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
