package br.com.fiap.dto;

import br.com.fiap.model.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioForm(
        @NotBlank @Size(min = 3, max = 100) String nomeUsuario,
        @Size(min = 8, max = 15) String telefone,
        @NotBlank @Email String email,
        @NotBlank @Size(min = 6, max = 50) String senha,
        @NotBlank String permissao
) {
    public Usuario toEntity() {
        Usuario u = new Usuario();
        u.setNomeUsuario(nomeUsuario);
        u.setTelefone(telefone);
        u.setEmail(email);
        u.setSenha(senha);
        u.setPermissao(permissao);
        return u;
    }
}