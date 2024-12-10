package imb.pr3.delivery.service;

import java.util.List;

import imb.pr3.delivery.entity.TipoComida;

public interface ITipoComidaService {

	public List<TipoComida> buscarTodos();
    public TipoComida buscarPorId(Integer id);
    public TipoComida guardar (TipoComida tipoComida);
    public boolean eliminar(Integer id);
    public boolean existe(Integer id);
    public List<TipoComida> buscarHabilitado(boolean habilitado);
	
}
