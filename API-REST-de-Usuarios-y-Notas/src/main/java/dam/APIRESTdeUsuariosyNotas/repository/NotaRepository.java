package dam.APIRESTdeUsuariosyNotas.repository;

import dam.APIRESTdeUsuariosyNotas.model.Nota;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NotaRepository extends JpaRepository<Nota, Long> {
    List<Nota> findByUsuarioId(Long usuarioId, Sort sort);
}