package imb.pr3.delivery.service;

import java.util.List;

import imb.pr3.delivery.entity.tipoComida;

public interface ITipoComidaService {

	List<tipoComida> buscartodo();
	tipoComida buscarPorId(Long id);
	tipoComida guardar(tipoComida carrito);
	tipoComida modificar(Long id, tipoComida carrito);
	boolean borrar(Long id);
	
}
