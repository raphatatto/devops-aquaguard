package br.com.fiap.dto;

import br.com.fiap.model.Usuario;
import jakarta.validation.constraints.NotNull;

public record UsuarioDTO(
        Long id,
        String nomeUsuario,
        String telefone,
        String email,
        String permissao
) {
        public UsuarioDTO(Usuario u) {
                this(u.getId(), u.getNomeUsuario(),
                        u.getTelefone(), u.getEmail(),
                        u.getPermissao());
        }
}