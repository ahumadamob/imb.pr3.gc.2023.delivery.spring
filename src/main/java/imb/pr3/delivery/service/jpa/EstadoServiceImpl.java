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
	public boolean existe(Integer id) {
		return (id == null) ? false : estadoRepository.existsById(id);
	}

	@Override
	public List<Estado> buscarTodos() throws Exception {

		try {
			List<Estado> estados = estadoRepository.findAll();
			return (List<Estado>) estados;
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}
	
	@Override
	public Optional<Estado> buscarPorId(Integer id) throws Exception {
		try {			
			Optional<Estado> estado = estadoRepository.findById(id);
			return estado;
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}		
	}

	@Override
	public boolean eliminar(Integer id) throws Exception{
		try {			
			if(estadoRepository.existsById(id)) {
				estadoRepository.deleteById(id);
				return true;
			}else {
				throw new Exception();
			}
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
		
	}

	@Override
	public Estado guardar(Estado estado) throws Exception {
		try {			
			estado = estadoRepository.save(estado);
			return estado;
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
		
	
	


}
