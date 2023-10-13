package imb.pr3.delivery.controller;


import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.http.ResponseUtil;
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

import imb.pr3.delivery.entity.Estado;
import imb.pr3.delivery.service.IEstadoService;
import jakarta.validation.ConstraintViolationException;


@RestController
@RequestMapping(path = "api/estado")
public class EstadoController {
	
	
	private IEstadoService EstadoService;
	
	public EstadoController(IEstadoService estadoService) {
		this.EstadoService = estadoService;
	}

	@GetMapping("")
	public ResponseEntity<ApiResponse<List<Estado>>> obtenerTodosEstados() {
	    List<Estado> estados = EstadoService.findAll();
	    return estados.isEmpty() ? ResponseUtil.notFound("No se encontraron datos para Cliente") : ResponseUtil.success(estados));
	}

	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<Optional<Estado>>> obtenerEstadoPorId(@PathVariable Integer id){
		Optional<Estado> estado = EstadoService.findById(id);
		return estado.isPresent() ? ResponseUtil.success(estado) : ResponseUtil.notFound("Cliente no encontrado");
	}
	
	@PostMapping("")
	public ResponseEntity<ApiResponse<Estado>> crearCliente(@RequestBody Estado estado){
		return EstadoService.existe(estado.getId()) ? ResponseUtil.badRequest("Debe ingresar un nombre") : ResponseUtil.created(EstadoService.save(estado));
	}
	
	@PutMapping("")
	public ResponseEntity<ApiResponse<Estado>> modificarCliente(@RequestBody Estado estado){
		return EstadoService.existe(estado.getId()) ?  ResponseUtil.success(EstadoService.save(estado)) 
				: ResponseUtil.badRequest("No existe estado con el id especificado");
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Boolean>> eliminarEstado(@PathVariable Integer id) {
		return EstadoService.existe(id) ? ResponseUtil.success(EstadoService.delete(id)) : ResponseUtil.badRequest("El ID no existe");
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<Estado>> handleException(Exception ex) {    	
	    return ResponseUtil.badRequest(ex.getMessage());
	}
	    
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ApiResponse<Estado>> handleConstraintViolationException(ConstraintViolationException ex) {
	    return ResponseUtil.handleConstraintException(ex);
	}    

}
