package imb.pr3.delivery.controller;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
import imb.pr3.delivery.util.ResponseUtil;
import jakarta.validation.ConstraintViolationException;

@RestController
@RequestMapping(path = "api/estado")
public class EstadoController {
	@Autowired
	IEstadoService estadoService;

	// buscarTodos
	@GetMapping("")
	public ResponseEntity<APIResponse<List<Estado>>> obtenerTodosLosClientes() throws Exception {
		List<Estado> estado = estadoService.findAll();
		return estado.isEmpty() ? ResponseUtil.notFound("No hay estados disponibles")
		           : ResponseUtil.success(estado); 
	}
	@GetMapping("{id}")
	public ResponseEntity<APIResponse<Optional<Estado>>> buscarEstadoPorId(@PathVariable("id") Integer id){
		Optional<Estado> estado=  estadoService.findById(id);
		return estado == null ? ResponseUtil.notFound("No se encontró el estado con el identificador proporcionado")
				: ResponseUtil.success(estado);	
	}
	@PostMapping
    public ResponseEntity<APIResponse<Estado>> crearEstado(@RequestBody Estado estado, BindingResult result) {
        return estadoService.existe(estado.getId()) ? ResponseUtil.badRequest("No se puede crear un estado, el ID ingresado ya existe")
        		: ResponseUtil.success(estado);
     }
	@PutMapping
	public ResponseEntity<APIResponse<Estado>> modificarEstado(@RequestBody Estado estado) {
		return estadoService.existe(estado.getId()) ? ResponseUtil.success(estadoService.save(estado))
				: ResponseUtil.badRequest("No se puede actualizar estado, el ID ingresado no ha sido creado");
	}
	@DeleteMapping("{id}")
	public ResponseEntity<APIResponse<Estado>> eliminarEstado(@PathVariable("id") Integer id) {
		 return estadoService.existe(id) ? ResponseUtil.success(null):
			 ResponseUtil.badRequest("No se puede actualizar estado, el ID ingresado no ha sido creado");			 		
	}
	// Excepciones
    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse<Estado>> handleException(Exception ex) {    	
    	return ResponseUtil.badRequest(ex.getMessage());
    }
    
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<APIResponse<Estado>> handleConstraintViolationException(ConstraintViolationException ex) {
    	return ResponseUtil.handleConstraintException(ex);
    } 
   
	
	

}
