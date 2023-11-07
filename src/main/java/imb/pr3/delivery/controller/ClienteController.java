package imb.pr3.delivery.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import imb.pr3.delivery.entity.Cliente;
import imb.pr3.delivery.service.IClienteService;
import imb.pr3.delivery.util.ResponseUtil;
import jakarta.validation.ConstraintViolationException;

@RestController
@RequestMapping(path = "api/cliente")
public class ClienteController {
	
	
	private IClienteService clienteService;
	
	public ClienteController(IClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@GetMapping("")
	public ResponseEntity<ApiResponse<List<Cliente>>> obtenerTodosClientes() {
	    List<Cliente> clientes = clienteService.findAll();
	    return clientes.isEmpty() ? ResponseUtil.notFound("No se encontraron datos para Cliente") : ResponseUtil.success(clientes);
	}

	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<Optional<Cliente>>> obtenerClientePorId(@PathVariable Integer id){
		Optional<Cliente> cliente = clienteService.findById(id);
		return cliente.isPresent() ? ResponseUtil.success(cliente) : ResponseUtil.notFound("Cliente no encontrado");
	}
	
	@PostMapping("")
	public ResponseEntity<ApiResponse<Cliente>> crearCliente(@RequestBody Cliente cliente){
		return clienteService.existe(cliente.getId()) ? ResponseUtil.badRequest("Debe ingresar un nombre") : ResponseUtil.created(clienteService.save(cliente));
	}
	
	@PutMapping("")
	public ResponseEntity<ApiResponse<Cliente>> modificarCliente(@RequestBody Cliente cliente){
		return clienteService.existe(cliente.getId()) ?  ResponseUtil.success(clienteService.save(cliente)) 
				: ResponseUtil.badRequest("No existe Cliente con el id especificado");
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Boolean>> eliminarCliente(@PathVariable Integer id) {
		return clienteService.existe(id) ? ResponseUtil.success(clienteService.delete(id)) : ResponseUtil.badRequest("El ID no existe");
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<Cliente>> handleException(Exception ex) {    	
	    return ResponseUtil.badRequest(ex.getMessage());
	}
	    
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ApiResponse<Cliente>> handleConstraintViolationException(ConstraintViolationException ex) {
	    return ResponseUtil.handleConstraintException(ex);
	}    

}
