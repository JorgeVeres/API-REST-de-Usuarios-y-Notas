package dam.APIRESTdeUsuariosyNotas.model;

import jakarta.persistence.*;
import java.util.*;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "usuarios", uniqueConstraints = {
    @UniqueConstraint(columnNames = "email")
})
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "Máximo 100 caracteres")
    private String nombre;
    
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Formato de email inválido")
    @Column(unique = true)
    private String email;
    
    @NotBlank(message = "La contraseña es obligatoria")
    private String passwordHash;
    
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Nota> notas = new ArrayList<>();

    // Constructores, Getters y Setters
    public Usuario() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public List<Nota> getNotas() {
        return notas;
    }

    public void setNotas(List<Nota> notas) {
        this.notas = notas;
    }

    
    public void addNota(Nota nota) {
        notas.add(nota);
        nota.setUsuario(this);
    }
}