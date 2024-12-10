package imb.pr3.delivery.controller;

import java.util.ArrayList;
import java.util.List;

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

import imb.pr3.delivery.entity.Comida;
import imb.pr3.delivery.entity.TipoComida;
import imb.pr3.delivery.service.jpa.ComidaServiceImpl;
import imb.pr3.delivery.util.ResponseUtil;
import jakarta.validation.ConstraintViolationException;

@RestController
@RequestMapping(path = "api/v1/comida")
public class ComidaController {
	@Autowired
	private ComidaServiceImpl ComidaService;
	List<String> errorMessages = new ArrayList<>();

	@GetMapping(path = "")
	public ResponseEntity<ApiResponse<List<Comida>>> buscarTodos() {
		List<Comida> comida = ComidaService.buscarTodos();
		return comida.isEmpty() ? ResponseUtil.notFound("No hay comidas guardadas") : ResponseUtil.success(comida);

	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<Comida>> obtenerComidaPorId(@PathVariable("id") Integer id) {
		Comida comida = ComidaService.buscarPorId(id);
		return comida == null ? ResponseUtil.notFound("No se encontró la comida con el identificador proporcionado")
				: ResponseUtil.success(comida);
	}

	@PostMapping
	public ResponseEntity<ApiResponse<Comida>> guardarComida(@RequestBody Comida comida) throws Exception {
		return comida.getNombre().isEmpty() ? ResponseUtil.badRequest("Debe ingresar un nombre")
				: ResponseUtil.created(ComidaService.guardar(comida));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<Comida>> modificarComida(@PathVariable("id") Integer id,
			@RequestBody Comida comida) throws Exception {
		return ComidaService.existe(comida.getId()) ? ResponseUtil.success(ComidaService.guardar(comida))
				: ResponseUtil.badRequest("No existe la comida con el id especificado");
	}

	
	@GetMapping("mostrar/{nombre}")
	public ResponseEntity<ApiResponse<List<Comida>>> buscarComidaPorNombre(@PathVariable("nombre") String nombre) {
		List<Comida> ComidaDeshabilitadas = ComidaService.bucarComidaPorNombre(nombre);
		return ComidaDeshabilitadas.isEmpty() ? ResponseUtil.notFound("No hay comidas Deshabilitadas ")
				: ResponseUtil.success(ComidaDeshabilitadas);
	}

	

	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<Comida>> handleException(Exception ex) {
		return ResponseUtil.badRequest(ex.getMessage());
	}
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ApiResponse<Comida>> handleConstraintViolationException(ConstraintViolationException ex) {
		return ResponseUtil.handleConstraintException(ex);
	}
}
