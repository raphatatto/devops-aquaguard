package br.com.fiap.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_aqua_usuario",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "email"),
                @UniqueConstraint(columnNames = "telefone")
        })
public class Usuario {

    @Id
    @SequenceGenerator(name = "usuario_seq", sequenceName = "USUARIO_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_seq")
    @Column(name = "id_usuario")
    private Long id;

    @NotBlank @Size(min = 3, max = 100)
    @Column(name = "nome_usuario", nullable = false, length = 100)
    private String nomeUsuario;

    @Size(min = 8, max = 15)
    @Column(name = "telefone", length = 15)
    private String telefone;

    @NotBlank @Email
    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @NotBlank @Size(min = 6, max = 50)
    @Column(name = "senha", length = 50)
    private String senha;

    @NotBlank
    @Column(name = "permissao", nullable = false, length = 50)
    private String permissao;

    // getters & setters
}