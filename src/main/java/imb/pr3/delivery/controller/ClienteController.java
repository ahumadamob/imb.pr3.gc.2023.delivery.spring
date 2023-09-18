package imb.pr3.delivery.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import imb.pr3.delivery.entity.Cliente;
import imb.pr3.delivery.service.IClienteService;

@RestController
@RequestMapping(path = "api/cliente")
public class ClienteController {
	
	
	private IClienteService clienteService;
	
	public ClienteController(IClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@GetMapping("")
	public ResponseEntity<ApiResponse<List<Cliente>>> obtenerTodosLosClientes() throws Exception{
		List<String> messages = new ArrayList<>();
		try {
	        List<Cliente> clientes = clienteService.findAll();

	        if (clientes.isEmpty()) {
	            messages.add("Status 404");
	            messages.add("No se encontraron clientes.");
	            ApiResponse<List<Cliente>> response = new ApiResponse<>(404, messages, null);
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	        }

	        else{ 
	        	messages.add("Status 200");
	        	messages.add("Clientes encontrados con éxito.");
		        ApiResponse<List<Cliente>> response = new ApiResponse<>(200, messages, clientes);
		        return ResponseEntity.status(HttpStatus.OK).body(response);
	        }
	       
	       
	    } catch (Exception e) {
	        messages.add("Status 500");
	        messages.add("Hubo un error en el servidor.");
	        messages.add("Revise los datos enviados.");
	        ApiResponse<List<Cliente>> response = new ApiResponse<>(500, messages, null);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	    }
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<Cliente>> obtenerClientePorId(@PathVariable Long id) throws Exception{
		
		List<String> messages = new ArrayList<>();
		try {
			Optional<Cliente> cliente = clienteService.findById(id);
	        if (!cliente.isPresent()) {
	            messages.add("Status 404");
	            messages.add("No se ha encontrado ningún cliente con el ID proporcionado.");
	            ApiResponse<Cliente> response = new ApiResponse<>(404, messages, null);
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	        }else {
	        	messages.add("Status 200");
	 	        messages.add("Cliente encontrado con éxito.");
	 	        ApiResponse<Cliente> response = new ApiResponse<>(200, messages, cliente.get());
	 	        return ResponseEntity.status(HttpStatus.OK).body(response);
	        }

	       
	    } catch (Exception e) {
	        messages.add("Status 500");
	        messages.add("Hubo un error en el servidor.");
	        messages.add("Revise los datos enviados.");
	        ApiResponse<Cliente> response = new ApiResponse<>(500, messages, null);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
	    }
	}
	
	@PostMapping("")
	public ResponseEntity<ApiResponse<Cliente>> crearCliente(@RequestBody Cliente cliente) throws Exception{
		List<String> messages = new ArrayList<>();
		
		try {
	        if (cliente.getNombre() != null && !cliente.getNombre().isEmpty()) {
	            Cliente clienteModificado = clienteService.save(cliente);
	            messages.add("Status 200");
	            messages.add("Entidad creada con éxito");
	            ApiResponse<Cliente> response = new ApiResponse<>(200, messages, clienteModificado);
	            return ResponseEntity.status(HttpStatus.OK).body(response);
	        } else {
	            messages.add("El nombre del cliente es obligatorio.");
	            ApiResponse<Cliente> response = new ApiResponse<>(400, messages, null);
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	        }
	    } catch (Exception e) {
	        messages.add("Status 400");
	        messages.add("Hubo un error en el servidor.");
	        messages.add("Revise los datos enviados.");
	        ApiResponse<Cliente> response = new ApiResponse<>(400, messages, null);
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	    }
	}
	
	@PutMapping("")
	public ResponseEntity<ApiResponse<Cliente>> modificarCliente(@RequestBody Cliente cliente) throws Exception{
		List<String> messages = new ArrayList<>();

	    try {
	        if (cliente.getNombre() != null && !cliente.getNombre().isEmpty()) {
	            Cliente clienteModificado = clienteService.save(cliente);
	            messages.add("Status 200");
	            messages.add("Entidad modificada con éxito");
	            ApiResponse<Cliente> response = new ApiResponse<>(200, messages, clienteModificado);
	            return ResponseEntity.status(HttpStatus.OK).body(response);
	        } else {
	            messages.add("El nombre del cliente es obligatorio.");
	            ApiResponse<Cliente> response = new ApiResponse<>(400, messages, null);
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	        }
	    } catch (Exception e) {
	        messages.add("Status 400");
	        messages.add("Hubo un error en el servidor.");
	        messages.add("Revise los datos enviados.");
	        ApiResponse<Cliente> response = new ApiResponse<>(400, messages, null);
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	    }
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Cliente>> eliminarCliente(@PathVariable Long id) throws Exception {
		List<String> messages = new ArrayList<>();
		Optional<Cliente> cliente = clienteService.findById(id);
		try {
			if(cliente.isPresent()) {
				messages.add("Status 200");
				messages.add("Entidad eliminada con éxito");
				//ELIMINO LA ENTIDAD
				clienteService.delete(id);
				ApiResponse<Cliente> response = new ApiResponse<Cliente>(200,messages,null);
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}else{
				messages.add("Status 400");
				messages.add("Hubo un problema con la solicitud.");
				messages.add("ID no encontrado");
				ApiResponse<Cliente> response = new ApiResponse<Cliente>(400,messages,null);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			}
		}catch (Exception e) {
	        messages.add("Status 400");
	        messages.add("Hubo un error en el servidor.");
	        messages.add("Revise los datos enviados.");
	        ApiResponse<Cliente> response = new ApiResponse<>(400, messages, null);
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	    }
	}

}
