package br.com.fiap.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_aqua_regiao")
public class Regiao {

    @Id
    @SequenceGenerator(name = "regiao_seq", sequenceName = "REGIAO_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "regiao_seq")
    @Column(name = "id_regiao")
    private Long id;

    @NotBlank
    @Size(max = 50)
    @Column(name = "nm_regiao", nullable = false, length = 50)
    private String nome;

    @NotBlank @Size(max = 50)
    @Column(name = "nm_cidade", nullable = false, length = 50)
    private String cidade;

    @Size(max = 50)
    @Column(name = "coordenadas_lat", length = 50)
    private String coordenadasLat;

    @Size(max = 50)
    @Column(name = "coordenadas_lng", length = 50)
    private String coordenadasLng;

    @OneToMany(mappedBy = "regiao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Alerta> alertas = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sensor", nullable = false)
    private Sensor sensor;

}