package br.com.fiap.dto;

import br.com.fiap.model.Regiao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public record   RegiaoDTO(
        Long id,
        String nome,
        String cidade,
        String coordenadasLat,
        String coordenadasLng

) {
    public RegiaoDTO(Regiao r) {
        this(r.getId(), r.getNome(), r.getCidade(),
                r.getCoordenadasLat(), r.getCoordenadasLng());
    }

}
