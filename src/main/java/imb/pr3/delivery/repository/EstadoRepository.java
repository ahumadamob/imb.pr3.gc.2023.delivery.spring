package imb.pr3.delivery.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import imb.pr3.delivery.entity.Estado;
public interface EstadoRepository extends JpaRepository<Estado,Integer> {
	public List<Estado>findByHabilitado(boolean habilitado);

}
