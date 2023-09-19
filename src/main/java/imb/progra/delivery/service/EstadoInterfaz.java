package imb.progra.delivery.service;

import java.util.List;

import imb.progra.delivery.entity.Estado;

public interface EstadoInterfaz {
	public List<Estado> obtenerTodas();
	public void guardarEstado(Estado estado);
	public void eliminarEstado(Integer id);
	public Estado buscar(Integer id);

}
