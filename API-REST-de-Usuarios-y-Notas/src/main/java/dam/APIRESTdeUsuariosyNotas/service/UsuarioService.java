package dam.APIRESTdeUsuariosyNotas.service;

import dam.APIRESTdeUsuariosyNotas.model.Usuario;

public interface UsuarioService extends CrudService<Usuario, Long> {
    boolean existsByEmail(String email);
}