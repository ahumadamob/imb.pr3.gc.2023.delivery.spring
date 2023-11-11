package imb.pr3.delivery.service;

import java.util.List;
import java.util.Optional;

import imb.pr3.delivery.entity.Cliente;

public interface IClienteService {
	List<Cliente> findAll();
	Optional<Cliente> findById (Integer id);
	boolean delete (Integer id);
	Cliente save(Cliente cliente) ;
	boolean existe(Integer id);
}
