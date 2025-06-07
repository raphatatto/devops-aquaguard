package br.com.fiap.service;

import br.com.fiap.dto.AlertaDTO;
import br.com.fiap.dto.AlertaForm;
import br.com.fiap.model.Alerta;
import br.com.fiap.model.Regiao;
import br.com.fiap.repository.AlertaRepository;
import br.com.fiap.repository.RegiaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



@Service
public class AlertaService {

    @Autowired
    private AlertaRepository alertaRepo;
    @Autowired
    private RegiaoRepository regiaoRepo;

    @Transactional(readOnly = true)
    public List<AlertaDTO> listar() {
        return alertaRepo.findAll()
                .stream()
                .map(AlertaDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<AlertaDTO> buscarPorId(Long id) {
        return alertaRepo.findById(id)
                .map(AlertaDTO::new);
    }

    @Transactional
    public AlertaDTO criar(AlertaForm form) {
        Regiao regiao = regiaoRepo.findById(form.regiaoId())
                .orElseThrow(() -> new IllegalArgumentException("Região não encontrada"));
        Alerta entidade = form.toEntity(regiao);
        Alerta salvo    = alertaRepo.save(entidade);
        return new AlertaDTO(salvo);
    }

    @Transactional
    public Optional<AlertaDTO> atualizar(Long id, AlertaForm form) {
        return alertaRepo.findById(id)
                .map(existing -> {
                    Regiao regiao = regiaoRepo.findById(form.regiaoId())
                            .orElseThrow(() -> new IllegalArgumentException("Região não encontrada"));
                    existing.setNivelRisco(form.nivelRisco());
                    existing.setDescricao(form.descricao());
                    existing.setData(form.data());
                    existing.setRegiao(regiao);
                    Alerta updated = alertaRepo.save(existing);
                    return new AlertaDTO(updated);
                });
    }

    @Transactional
    public boolean deletar(Long id) {
        return alertaRepo.findById(id)
                .map(existing -> {
                    alertaRepo.delete(existing);
                    return true;
                })
                .orElse(false);
    }
}