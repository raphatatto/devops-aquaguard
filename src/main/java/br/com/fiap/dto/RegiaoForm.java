package br.com.fiap.dto;

import br.com.fiap.model.Regiao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.jetbrains.annotations.NotNull;

public record RegiaoForm(
        @NotBlank @Size(max = 50) String nome,
        @NotBlank @Size(max = 50) String cidade,
        @Size(max = 50) String coordenadasLat,
        @Size(max = 50) String coordenadasLng,
        @NotNull Long sensorId
) {
    public Regiao toEntity() {
        Regiao r = new Regiao();
        r.setNome(nome);
        r.setCidade(cidade);
        r.setCoordenadasLat(coordenadasLat);
        r.setCoordenadasLng(coordenadasLng);

        return r;
    }
}