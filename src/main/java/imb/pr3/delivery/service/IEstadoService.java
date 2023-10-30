package imb.pr3.delivery.service;

import java.util.List;
import java.util.Optional;

import imb.pr3.delivery.entity.Estado;

public interface IEstadoService {


	public List<Estado> buscarTodos() throws Exception ;

	public Optional<Estado> buscarPorId(Integer id) throws Exception;
	

	boolean existe(Integer id);
	
	public boolean eliminar(Integer id) throws Exception;
	
	public Estado guardar (Estado estado) throws Exception;
}
