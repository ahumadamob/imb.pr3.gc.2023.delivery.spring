package imb.pr3.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import imb.pr3.delivery.entity.TipoComida;

import java.util.List;

@Repository
public interface TipoComidaRepository extends JpaRepository<TipoComida, Integer> {
	public List<TipoComida> findByHabilitado(boolean habilitado);

	public List<TipoComida> findByNombre(String nombre);

}
