package imb.progra.delivery.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import imb.progra.delivery.entity.Estado;
import imb.progra.delivery.repository.Repositorio;
@Service
@Primary

public class EstadoMySQL implements EstadoInterfaz{
	
	@Autowired
	Repositorio repo;
	

	@Override
	public List<Estado> obtenerTodas() {
		
		return repo.findAll();
		
	}




	@Override
	public void eliminarEstado(Integer id) {
		repo.deleteById(id);
		
	}







	@Override
	public void guardarEstado(Estado estado) {
		repo.save(estado);
		
	}




	@Override
	public Estado buscar(Integer id) {
		Optional<Estado> opt=repo.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}else {
			return new Estado();
		}
	
	}
	


}
