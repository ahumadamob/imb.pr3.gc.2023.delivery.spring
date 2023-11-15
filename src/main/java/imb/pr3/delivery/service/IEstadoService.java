package imb.pr3.delivery.service;

import java.util.List;
import java.util.Optional;

import imb.pr3.delivery.entity.Estado;

public interface IEstadoService {


	public List<Estado> buscarTodos() throws Exception ;
	Estado buscarPorId(Integer id) throws Exception;
	public boolean eliminar(Integer id) throws Exception;
	public Estado guardar (Estado estado) throws Exception;
	boolean existe(Integer id);
	public List<Estado> buscarPorHabilitado(boolean habilitado);
}
