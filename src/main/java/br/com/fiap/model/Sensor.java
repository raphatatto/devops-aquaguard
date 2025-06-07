package br.com.fiap.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Id;
@Getter
@Setter
@Entity
@Table(name = "tb_aqua_sensor")
public class Sensor {

    @Id
    @SequenceGenerator(
            name = "sensor_seq",
            sequenceName = "SENSOR_SEQ",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sensor_seq")
    @Column(name = "id_sensor")
    private Long id;
    @NotBlank
    @Size(max = 50)
    @Column(name = "tipo", nullable = false, length = 50)
    private String tipo;

    @NotBlank
    @Size(max = 20)
    @Column(name = "status", nullable = false, length = 20)
    private String status;

}
