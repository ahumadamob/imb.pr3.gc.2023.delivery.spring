package imb.pr3.delivery.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imb.pr3.delivery.entity.Estado;
import imb.pr3.delivery.repository.EstadoRepository;
import imb.pr3.delivery.service.IEstadoService;

@Service
public class EstadoServiceImpl implements IEstadoService {

	@Autowired
	EstadoRepository estadoRepository;

	@Override
	public List<Estado> findAll() throws Exception {
		List<Estado> estados = estadoRepository.findAll();
		return (List<Estado>) estados;

	}

	@Override
	public Optional<Estado> findById(Integer id) {
		Optional<Estado> estado = estadoRepository.findById(id);
		return estado;

	}

	@Override
	public boolean delete(Integer id) {
	    estadoRepository.deleteById(id);
		return true;
	}

	@Override
	public Estado save(Estado estado) {
	     estado = estadoRepository.save(estado);
		return estado;
	}

	@Override
	public boolean existe(Integer id) {
		return (id == null) ? false: estadoRepository.existsById(id);
	}

}
