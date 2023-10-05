package imb.pr3.delivery.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import imb.pr3.delivery.entity.Cliente;
import imb.pr3.delivery.repository.ClienteRepository;
import imb.pr3.delivery.service.IClienteService;

@Service
public class ClienteServiceImpl implements IClienteService{

	@Autowired
	ClienteRepository clienteRepository;
	
	@Override
	public List<Cliente> findAll() throws Exception {
		try {
			List<Cliente> clientes = clienteRepository.findAll();
			return (List<Cliente>) clientes;
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Optional<Cliente> findById(Long id) throws Exception {
		try {			
			Optional<Cliente> cliente = clienteRepository.findById(id);
			return cliente;
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public boolean delete(Long id) throws Exception {
		try {			
			if(clienteRepository.existsById(id)) {
				clienteRepository.deleteById(id);
				return true;
			}else {
				throw new Exception();
			}
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
	public Cliente save(Cliente cliente) throws Exception {
		try {			
			cliente = clienteRepository.save(cliente);
			return cliente;
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	@Override
    public boolean existe(Long id) {
    	return (id == null) ? false: clienteRepository.existsById(id);
    }

}
