package imb.pr3.delivery.controller;

import java.util.List;
import java.util.Optional;


import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
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
import imb.pr3.delivery.util.ResponseUtil;

@RestController
@RequestMapping(path = "api/estado/")
public class EstadoController {
	@Autowired
	IEstadoService estadoService;

	// buscarTodos
	@GetMapping("")
	public ResponseEntity<ApiResponse<List<Estado>>> obtenerTodosLosEstados() throws Exception {
		List<Estado> estado = estadoService.buscarTodos();
		return estado.isEmpty() ? ResponseUtil.notFound("No hay estados disponibles")
		           : ResponseUtil.success(estado); 
	}
	@GetMapping("{id}") // Maneja solicitudes GET en la ruta que incluye un parámetro llamado "id"
	public ResponseEntity<ApiResponse<Optional<Estado>>> buscarEstadoPorId(@PathVariable("id") Integer id) throws Exception {
		// El método "buscarEstadoPorId" toma un parámetro "id" de la URL y devuelve una ResponseEntity que contiene ApiResponse con un Optional de Estado

		Optional<Estado> estado = estadoService.buscarPorId(id);
		// Llama al método "buscarPorId" del objeto estadoService para buscar un Estado por el "id" proporcionado y almacena el resultado en un Optional

		return estado == null ? // Comprueba si el estado no se encontró (null)
				ResponseUtil.notFound("No se encontró el estado con el identificador proporcionado") :
				ResponseUtil.success(estado);
		// Si no se encontró el estado (null), devuelve una respuesta NotFound con un mensaje de error.
		// Si se encontró el estado, devuelve una respuesta exitosa con el estado encontrado.
	}

	@PostMapping("")
	public ResponseEntity<ApiResponse<Estado>> crearEstado(@RequestBody Estado estado) throws Exception{
		return estadoService.existe(estado.getId()) ? ResponseUtil.badRequest("Debe ingresar un nombre") : 
			ResponseUtil.created(estadoService.guardar(estado));
	}
	
	@PutMapping
	public ResponseEntity<ApiResponse<Estado>> modificarEstado(@RequestBody Estado estado) throws Exception {
		return estadoService.existe(estado.getId()) ? ResponseUtil.success(estadoService.guardar(estado))
				: ResponseUtil.badRequest("No se puede actualizar estado, el ID ingresado no ha sido creado");
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Boolean>> eliminarEstado(@PathVariable Integer id) throws Exception {
		return estadoService.existe(id) ? ResponseUtil.success(estadoService.eliminar(id)) : ResponseUtil.badRequest("El ID no existe");
	}
	
	// Excepciones
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Estado>> handleException(Exception ex) {
    	return ResponseUtil.badRequest(ex.getMessage());
    }

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ApiResponse<Estado>> handleConstraintViolationException(ConstraintViolationException ex) {
		return ResponseUtil.handleConstraintException(ex);
	}
}
