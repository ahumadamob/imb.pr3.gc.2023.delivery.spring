package imb.pr3.delivery.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imb.pr3.delivery.entity.tipoComida;
import imb.pr3.delivery.service.ITipoComidaService;
import imb.pr3.delivery.repository.tipoComidaRepository;

@Service
public class tipoComidaServiceImpl implements ITipoComidaService{
	
	@Autowired
	private tipoComidaRepository tipoComidaRepository;

	@Override
	public List<tipoComida> buscartodo() {
		List<tipoComida>carrito=tipoComidaRepository.findAll();
		return carrito;
	}

	@Override
	public tipoComida buscarPorId(Long id) {
		Optional<tipoComida>tipoComidaOpc=tipoComidaRepository.findById(id);
		return tipoComidaOpc.get();
	}

	@Override
	public tipoComida guardar(tipoComida comida) {
		comida=tipoComidaRepository.save(comida);
		return comida;
	}

	@Override
	public tipoComida modificar(Long id, tipoComida carrito) {
		Optional<tipoComida>tipoComidaOpc=tipoComidaRepository.findById(id);
		tipoComida tipoComidaMod=tipoComidaOpc.get();
		tipoComidaMod=tipoComidaRepository.save(carrito);
		return tipoComidaMod;
	}

	@Override
	public boolean borrar(Long id) {
		tipoComidaRepository.deleteById(id);
		return true;
	}

}
