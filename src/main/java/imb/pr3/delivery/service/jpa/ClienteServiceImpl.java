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
	public List<Cliente> findAll()  {
			List<Cliente> clientes = clienteRepository.findAll();
			return (List<Cliente>) clientes;

	}

	@Override
	public Optional<Cliente> findById(Integer id)  {	
			Optional<Cliente> cliente = clienteRepository.findById(id);
			return cliente;

	}

	@Override
	public boolean delete(Integer id)  {
			clienteRepository.deleteById(id);
			return true;

	}

	@Override
	public Cliente save(Cliente cliente) {			
			cliente = clienteRepository.save(cliente);
			return cliente;

	}

	@Override
    public boolean existe(Integer id) {
    	return (id == null) ? false: clienteRepository.existsById(id);
    }

}
