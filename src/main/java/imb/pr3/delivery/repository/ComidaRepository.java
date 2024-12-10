package imb.pr3.delivery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import imb.pr3.delivery.entity.Comida;

@Repository
public interface ComidaRepository extends JpaRepository<Comida, Integer> {

	public List<Comida> findByNombre(String nombre);

}
