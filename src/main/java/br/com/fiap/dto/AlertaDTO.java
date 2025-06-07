package br.com.fiap.dto;


import br.com.fiap.model.Alerta;

import java.time.LocalDate;

public record AlertaDTO(
        Long id,
        String nivelRisco,
        String descricao,
        LocalDate data,
        Long regiaoId
) {
    public AlertaDTO(Alerta a) {
        this(a.getId(), a.getNivelRisco(),
                a.getDescricao(), a.getData(),
                a.getRegiao().getId());
    }
}
