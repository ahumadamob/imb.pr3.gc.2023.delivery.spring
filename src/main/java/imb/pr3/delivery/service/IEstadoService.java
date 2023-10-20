package imb.pr3.delivery.service;

import java.util.List;
import java.util.Optional;

import imb.pr3.delivery.entity.Estado;

public interface IEstadoService {
	List<Estado> findAll() throws Exception;
	Optional<Estado> findById (Integer id);
	boolean delete (Integer id);
	Estado save(Estado estado) ;
	boolean existe(Integer id);
}
