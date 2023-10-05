package imb.pr3.delivery.service;

import java.util.List;
import java.util.Optional;

import imb.pr3.delivery.entity.Cliente;

public interface IClienteService {
	List<Cliente> findAll() throws Exception;
	Optional<Cliente> findById (Long id) throws Exception;
	boolean delete (Long id) throws Exception;
	Cliente save(Cliente cliente) throws Exception;
	boolean existe(Long id);
}
