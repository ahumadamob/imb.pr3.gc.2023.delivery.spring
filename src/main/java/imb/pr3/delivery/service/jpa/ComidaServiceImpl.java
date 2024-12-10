package imb.pr3.delivery.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import imb.pr3.delivery.entity.Comida;
import imb.pr3.delivery.repository.ComidaRepository;
import imb.pr3.delivery.service.IComidaService;

@Service
public class ComidaServiceImpl implements IComidaService {
	@Autowired
	ComidaRepository comidarepository;

	@Override
	public List<Comida> buscarTodos() {
		List<Comida> comidas = comidarepository.findAll();
		
		return comidas;
	}

	@Override
	public Comida buscarPorId(Integer id) {
		Optional<Comida> opt = comidarepository.findById(id);
		return opt.orElse(null);
	}

	@Override
	public Comida guardar(Comida comida) {
		comida = comidarepository.save(comida);
		return comida;
	}

	@Override
	public boolean eliminar(Integer id) {
		comidarepository.deleteById(id);
		return true;
	}

	@Override
	public boolean existe(Integer id) {

		return (id == null) ? false : comidarepository.existsById(id);
	}

	

	@Override
	public List<Comida> bucarComidaPorNombre(String nombre) {
		return comidarepository.findByNombre(nombre);
	}
}
