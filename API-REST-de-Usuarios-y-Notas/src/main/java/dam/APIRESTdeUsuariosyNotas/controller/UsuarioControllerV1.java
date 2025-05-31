package dam.APIRESTdeUsuariosyNotas.controller;

import dam.APIRESTdeUsuariosyNotas.model.Usuario;
import dam.APIRESTdeUsuariosyNotas.service.UsuarioService;
import java.util.List;

import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioControllerV1 {
    
    private final UsuarioService usuarioService;
    
    public UsuarioControllerV1(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    
    @GetMapping
    public ResponseEntity<List<Usuario>> getAll() {
        return ResponseEntity.ok(usuarioService.getAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable Long id) {
        return usuarioService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Usuario> create(@Validated @RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(usuarioService.save(usuario));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(
            @PathVariable Long id, 
            @Validated @RequestBody Usuario usuario) {
        
        return ResponseEntity.ok(usuarioService.update(id, usuario));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        usuarioService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}