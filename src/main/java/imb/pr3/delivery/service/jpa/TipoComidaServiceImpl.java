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



	@Override
	public TipoComida guardar(TipoComida comida) {
		comida=tipoComidaRepository.save(comida);
		return comida;
	}

	@Override
	public List<TipoComida> buscarTodos() {
		List<TipoComida>comida=tipoComidaRepository.findAll();
		return comida;
	}

	@Override
	public TipoComida buscarPorId(Integer id) {
		Optional<TipoComida> opt = tipoComidaRepository.findById(id);
        return opt.orElse(null);
	}

	@Override
	public boolean eliminar(Integer id) {
		tipoComidaRepository.deleteById(id);
		return true;	
	}

	@Override
	public boolean existe(Integer id) {
		return (id == null) ? false: tipoComidaRepository.existsById(id);
	}

}
