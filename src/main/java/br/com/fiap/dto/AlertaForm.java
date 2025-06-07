package br.com.fiap.dto;

import br.com.fiap.model.Alerta;
import br.com.fiap.model.Regiao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record AlertaForm(
        @NotBlank @Size(max = 50) String nivelRisco,
        @Size(max = 50) String descricao,
        LocalDate data,
        @NotNull Long regiaoId
) {
    public Alerta toEntity(Regiao regiao) {
        Alerta a = new Alerta();
        a.setNivelRisco(nivelRisco);
        a.setDescricao(descricao);
        a.setData(data);
        a.setRegiao(regiao);
        return a;
    }
}
