package dam.APIRESTdeUsuariosyNotas.controller;

import dam.APIRESTdeUsuariosyNotas.model.Nota;
import dam.APIRESTdeUsuariosyNotas.repository.NotaRepository;
import dam.APIRESTdeUsuariosyNotas.service.NotaService;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/notas")
public class NotaControllerV1 {
    
    private final NotaService notaService;
    private final NotaRepository notaRepository;
    
    public NotaControllerV1(NotaService notaService, NotaRepository notaRepository) {
        this.notaService = notaService;
        this.notaRepository = notaRepository;
    }
    
    @GetMapping
    public ResponseEntity<List<Nota>> getAll(
            @RequestParam(required = false) Long usuarioId,
            @RequestParam(defaultValue = "asc") String order) {
        
        if (usuarioId != null) {
            Sort sort = order.equalsIgnoreCase("desc") 
                ? Sort.by("fechaCreacion").descending() 
                : Sort.by("fechaCreacion").ascending();
            return ResponseEntity.ok(notaRepository.findByUsuarioId(usuarioId, sort));
        }
        return ResponseEntity.ok(notaService.getAll());
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Nota> getById(@PathVariable Long id) {
        return notaService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ResponseEntity<Nota> create(
            @RequestParam Long usuarioId,
            @Validated @RequestBody Nota nota) {
        
        // En una implementación real se asignaría el usuario a la nota
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(notaService.save(nota));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Nota> update(
            @PathVariable Long id, 
            @Validated @RequestBody Nota nota) {
        
        return ResponseEntity.ok(notaService.update(id, nota));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        notaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}