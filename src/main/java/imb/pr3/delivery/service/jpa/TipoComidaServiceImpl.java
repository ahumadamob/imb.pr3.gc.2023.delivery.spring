package imb.pr3.delivery.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imb.pr3.delivery.entity.TipoComida;
import imb.pr3.delivery.service.ITipoComidaService;
import imb.pr3.delivery.repository.TipoComidaRepository;

@Service
public class TipoComidaServiceImpl implements ITipoComidaService{
	
	
	@Autowired
	private TipoComidaRepository tipoComidaRepository;


	//Metodo para guarda la entidad TipoComida en la base de datos
	@Override
	public TipoComida guardar(TipoComida comida) {
		/* Utilizamos el método save del repositorio para persistir la entidad en la base de datos.
         Esto puede involucrar la inserción de un nuevo registro en la base de datos o la actualización de un registro existente.*/
		comida=tipoComidaRepository.save(comida);
		// Devolvemos la entidad actualizada después de la operación.
		return comida;
	}
	//Metodo para busca y devolver todas las entidades TipoComida en la base de datos
	@Override
	public List<TipoComida> buscarTodos() {
		// Utilizamos el método findAll del repositorio para recuperar todas las entidades almacenadas en la base de datos.
		List<TipoComida>comida=tipoComidaRepository.findAll();
		// Devolvemos la lista de entidades encontradas.
		return comida;
	}
	//MEtodo para busca una entidad TipoComida por su ID y la devuelve
	@Override
	public TipoComida buscarPorId(Integer id) {
		/* Utilizamos el método findById del repositorio para buscar la entidad por su ID.
        El resultado se envuelve en un objeto Optional, lo que nos permite manejar la posibilidad de que la entidad no se encuentre.*/
		Optional<TipoComida> opt = tipoComidaRepository.findById(id);
		// Devolvemos la entidad si se encuentra, o nulo si no se encuentra.
        return opt.orElse(null);
	}
	//Metodo para elimina una entidad TipoComida de la base de datos por su ID
	@Override
	public boolean eliminar(Integer id) {
		/* Utilizamos el método deleteById del repositorio para eliminar la entidad con el ID proporcionado.
        Esta operación elimina físicamente el registro de la base de datos.*/
		tipoComidaRepository.deleteById(id);
		// Devolvemos true después de eliminar la entidad con éxito.
		return true; 
	}
	//Metodo para verifica si una entidad TipoComida con el ID dado existe en la base de datos
	@Override
	public boolean existe(Integer id) {
		 // Comprobamos si el ID no es nulo y si existe en la base de datos utilizando el método existsById del repositorio.
		return (id == null) ? false: tipoComidaRepository.existsById(id);
	}

	@Override
	public List<TipoComida> buscarHabilitado(boolean habilitado) {

		return tipoComidaRepository.findByHabilitado(habilitado);
	}
	
	
	
	

}
