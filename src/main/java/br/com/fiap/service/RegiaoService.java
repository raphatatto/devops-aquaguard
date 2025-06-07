package br.com.fiap.service;


import br.com.fiap.dto.RegiaoDTO;
import br.com.fiap.dto.RegiaoForm;
import br.com.fiap.model.Regiao;
import br.com.fiap.model.Sensor;
import br.com.fiap.repository.RegiaoRepository;

import br.com.fiap.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class RegiaoService {

    @Autowired
    private RegiaoRepository repo;
    @Autowired
    private SensorRepository sensorRepo;

    @Transactional(readOnly = true)
    public Page<RegiaoDTO> listar(Pageable pageable, Optional<String> cidade) {
        Page<Regiao> page;
        if (cidade.isPresent()) {
            page = repo.findByCidadeContainingIgnoreCase(cidade.get(), pageable);
        } else {
            page = repo.findAll(pageable);
        }
        return page.map(RegiaoDTO::new);
    }
    @Transactional(readOnly = true)
    public Optional<RegiaoDTO> buscarPorId(Long id) {
        return repo.findById(id)
                .map(RegiaoDTO::new);
    }

    @Transactional
    public RegiaoDTO criar(RegiaoForm form) {
        Sensor sensor = sensorRepo.findById(form.sensorId())
                .orElseThrow(() -> new IllegalArgumentException("Sensor não encontrado"));
        Regiao entidade = form.toEntity();
        entidade.setSensor(sensor);
        Regiao salvo = repo.save(entidade);
        return new RegiaoDTO(salvo);
    }

    @Transactional
    public Optional<RegiaoDTO> atualizar(Long id, RegiaoForm form) {
        return repo.findById(id)
                .map(existing -> {
                    existing.setNome(form.nome());
                    existing.setCidade(form.cidade());
                    existing.setCoordenadasLat(form.coordenadasLat());
                    existing.setCoordenadasLng(form.coordenadasLng());
                    Regiao updated = repo.save(existing);
                    return new RegiaoDTO(updated);
                });
    }

    @Transactional
    public boolean deletar(Long id) {
        return repo.findById(id)
                .map(existing -> {
                    repo.delete(existing);
                    return true;
                })
                .orElse(false);
    }
}