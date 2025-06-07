package br.com.fiap.service;

import br.com.fiap.dto.UsuarioDTO;
import br.com.fiap.dto.UsuarioForm;
import br.com.fiap.model.Usuario;
import br.com.fiap.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repo;

    @Transactional(readOnly = true)
    public List<UsuarioDTO> listar() {
        return repo.findAll()
                .stream()
                .map(UsuarioDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Optional<UsuarioDTO> buscarPorId(Long id) {
        return repo.findById(id)
                .map(UsuarioDTO::new);
    }

    @Transactional
    public UsuarioDTO criar(UsuarioForm form) {
        Usuario entidade = form.toEntity();
        Usuario salvo    = repo.save(entidade);
        return new UsuarioDTO(salvo);
    }

    @Transactional
    public Optional<UsuarioDTO> atualizar(Long id, UsuarioForm form) {
        return repo.findById(id)
                .map(existing -> {
                    existing.setNomeUsuario(form.nomeUsuario());
                    existing.setTelefone(form.telefone());
                    existing.setEmail(form.email());
                    existing.setSenha(form.senha());
                    existing.setPermissao(form.permissao());
                    Usuario updated = repo.save(existing);
                    return new UsuarioDTO(updated);
                });
    }

    @Transactional
    public boolean deletar(Long id) {
        return repo.findById(id)
                .map(existing -> {
                    repo.delete(existing);
                    return true;
                })
                .orElse(false);
    }
}

