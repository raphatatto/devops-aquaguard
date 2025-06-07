package br.com.fiap.controller;


import br.com.fiap.dto.AuthRequest;
import br.com.fiap.dto.AuthResponse;
import br.com.fiap.model.Usuario;
import br.com.fiap.repository.UsuarioRepository;
import br.com.fiap.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepo;  // precisa de método findByEmail()

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest req) {
        // busca usuário
        Usuario u = usuarioRepo.findByEmail(req.email())
                .orElse(null);
        // valida senha
        if (u == null || !u.getSenha().equals(req.senha())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Email ou senha inválidos");
        }
        // gera token
        String token = jwtUtil.generateToken(u.getEmail());
        return ResponseEntity.ok(new AuthResponse(token, "Bearer"));
    }
}