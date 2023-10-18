package imb.pr3.delivery.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import imb.pr3.delivery.entity.TipoComida;
import imb.pr3.delivery.service.ITipoComidaService;
import imb.pr3.delivery.service.jpa.TipoComidaServiceImpl;
import imb.pr3.delivery.util.ResponseUtil;
import jakarta.validation.ConstraintViolationException;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path="api/v1/tipoComida")
public class TipoComidaControllerImpl {
	
	@Autowired
	private TipoComidaServiceImpl tipoComidaService;
	List<String> errorMessages = new ArrayList<>();
	
	@GetMapping("")
	public ResponseEntity<ApiResponse<List<TipoComida>>> buscarTodos() {
	    List<TipoComida> tipoComida = tipoComidaService.buscarTodos();
	    return tipoComida.isEmpty() ? ResponseUtil.notFound("No hay comidas guardadas")
	            : ResponseUtil.success(tipoComida);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<TipoComida>> obtenerComidaPorId(@PathVariable("id") Integer id){
		TipoComida tipoComida = tipoComidaService.buscarPorId(id);
		return tipoComida == null ? ResponseUtil.notFound("No se encontró la comida con el identificador proporcionado")
				: ResponseUtil.success(tipoComida);	
	}
	
	
	@PostMapping("")
	public ResponseEntity<ApiResponse<TipoComida>> guardarComida(@RequestBody TipoComida comida) throws Exception{
		return comida.getNombre().isEmpty() ? ResponseUtil.badRequest("Debe ingresar un nombre") : ResponseUtil.created(tipoComidaService.guardar(comida));

	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<TipoComida>> modificarComida(@PathVariable("id") Integer id,@RequestBody TipoComida comida) throws Exception{
		return tipoComidaService.existe(comida.getId()) ?  ResponseUtil.success(tipoComidaService.guardar(comida)) 
				: ResponseUtil.badRequest("No existe la comida con el id especificado");
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Boolean>> eliminarComida(@PathVariable Integer id) throws Exception {
		return tipoComidaService.existe(id) ? ResponseUtil.success(tipoComidaService.eliminar(id)) : ResponseUtil.badRequest("El ID no existe");
	}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<TipoComida>> handleException(Exception ex) {    	
	    return ResponseUtil.badRequest(ex.getMessage());
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ApiResponse<TipoComida>> handleConstraintViolationException(ConstraintViolationException ex) {
	    return ResponseUtil.handleConstraintException(ex);
	}    
	}