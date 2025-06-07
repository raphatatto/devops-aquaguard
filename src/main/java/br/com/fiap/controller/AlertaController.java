package br.com.fiap.controller;

import br.com.fiap.dto.AlertaDTO;
import br.com.fiap.dto.AlertaForm;
import br.com.fiap.model.Alerta;
import br.com.fiap.service.AlertaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/alertas")
public class AlertaController {

    @Autowired
    private AlertaService service;

    @GetMapping
    public List<AlertaDTO> getAll() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlertaDTO> getById(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<AlertaDTO> create(@Valid @RequestBody AlertaForm form) {
        AlertaDTO dto = service.criar(form);
        return ResponseEntity
                .created(URI.create("/api/alertas/" + dto.id()))
                .body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlertaDTO> update(@PathVariable Long id,
                                            @Valid @RequestBody AlertaForm form) {
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
