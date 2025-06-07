package br.com.fiap.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "tb_aqua_alerta")
public class Alerta {

    @Id
    @SequenceGenerator(name = "alerta_seq", sequenceName = "ALERTA_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "alerta_seq")
    @Column(name = "id_alerta")
    private Long id;

    @NotBlank
    @Size(max = 50)
    @Column(name = "nivel_risco", nullable = false, length = 50)
    private String nivelRisco;

    @Size(max = 50)
    @Column(name = "ds_alerta", length = 50)
    private String descricao;

    @Column(name = "dt_alerta")
    private LocalDate data;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_regiao", nullable = false)
    private Regiao regiao;
}