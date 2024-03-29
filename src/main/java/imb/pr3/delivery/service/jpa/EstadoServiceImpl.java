package imb.pr3.delivery.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imb.pr3.delivery.entity.Estado;
import imb.pr3.delivery.entity.TipoComida;
import imb.pr3.delivery.repository.EstadoRepository;
import imb.pr3.delivery.service.IEstadoService;

@Service
public class EstadoServiceImpl implements IEstadoService {

    @Autowired
    EstadoRepository estadoRepository;

    @Override
    public boolean existe(Integer id) {
        return (id == null) ? false : estadoRepository.existsById(id);
    }

    @Override
    public List<Estado> buscarTodos(){
        return estadoRepository.findAll();
    }

    @Override
    public Estado buscarPorId(Integer id){
    	Optional<Estado> opt = estadoRepository.findById(id);
        return opt.orElse(null);
    }

    @Override
    public boolean eliminar(Integer id){
            estadoRepository.deleteById(id);
            return true;
    }

    @Override
    public Estado guardar(Estado estado) throws Exception {
        return estadoRepository.save(estado);
    }

	@Override
	public List<Estado> buscarPorHabilitado(boolean habilitado) {
		return estadoRepository.findByHabilitado(habilitado);
	}
}
