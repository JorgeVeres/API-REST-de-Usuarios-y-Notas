package dam.APIRESTdeUsuariosyNotas.service;

import dam.APIRESTdeUsuariosyNotas.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UsuarioServiceImpl extends AbstractCrudService<Usuario, Long, UsuarioRepository> 
                               implements UsuarioService {
    
    public UsuarioServiceImpl(UsuarioRepository repository) {
        super(repository);
    }
    
    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }
}