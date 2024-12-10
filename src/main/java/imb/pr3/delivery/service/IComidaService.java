package imb.pr3.delivery.service;

import java.util.List;

import imb.pr3.delivery.entity.Comida;

public interface IComidaService {
	public List<Comida> buscarTodos();

	public Comida buscarPorId(Integer id);

	public Comida guardar(Comida comida);

	public boolean eliminar(Integer id);

	public boolean existe(Integer id);

	public List<Comida> bucarComidaPorNombre(String nombre);

}
