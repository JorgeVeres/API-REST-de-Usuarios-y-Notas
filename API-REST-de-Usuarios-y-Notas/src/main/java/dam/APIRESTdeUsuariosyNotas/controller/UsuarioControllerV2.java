package dam.APIRESTdeUsuariosyNotas.controller;

import dam.APIRESTdeUsuariosyNotas.model.Usuario;
import dam.APIRESTdeUsuariosyNotas.service.UsuarioService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2")
public class UsuarioControllerV2 {
    
    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;
    
    public UsuarioControllerV2(
            UsuarioService usuarioService, 
            PasswordEncoder passwordEncoder) {
        
        this.usuarioService = usuarioService;
        this.passwordEncoder = passwordEncoder;
    }
    
    @PostMapping("/sign-in")
    public ResponseEntity<Usuario> signIn(@RequestBody Usuario usuario) {
        usuario.setPasswordHash(passwordEncoder.encode(usuario.getPasswordHash()));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usuarioService.save(usuario));
    }
}