package dam.APIRESTdeUsuariosyNotas.service;

import dam.APIRESTdeUsuariosyNotas.model.Nota;
import dam.APIRESTdeUsuariosyNotas.repository.NotaRepository;
import org.springframework.stereotype.Service;

@Service
public class NotaServiceImpl extends AbstractCrudService<Nota, Long, NotaRepository> 
                            implements NotaService {

    public NotaServiceImpl(NotaRepository repository) {
        super(repository);
    }
}
