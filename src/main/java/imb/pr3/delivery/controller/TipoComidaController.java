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
@RequestMapping(path = "api/v1/tipocomida")
public class TipoComidaController {


	@Autowired
	private TipoComidaServiceImpl tipoComidaService;
	List<String> errorMessages = new ArrayList<>();

	@GetMapping(path="")
	public ResponseEntity<ApiResponse<List<TipoComida>>> buscarTodos() {
		List<TipoComida> tipocomida= tipoComidaService.buscarTodos();
		return tipocomida.isEmpty() ? ResponseUtil.notFound("No hay comidas guardadas")
				: ResponseUtil.success(tipocomida);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<TipoComida>> obtenerComidaPorId(@PathVariable("id") Integer id){
		TipoComida tipoComida = tipoComidaService.buscarPorId(id);
		return tipoComida == null ? ResponseUtil.notFound("No se encontró la comida con el identificador proporcionado")
				: ResponseUtil.success(tipoComida);
	}

	@PostMapping
	public ResponseEntity<ApiResponse<TipoComida>> guardarComida(@RequestBody TipoComida comida) throws Exception {
		return comida.getNombre().isEmpty() ? ResponseUtil.badRequest("Debe ingresar un nombre") : ResponseUtil.created(tipoComidaService.guardar(comida));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<TipoComida>> modificarComida(@PathVariable("id") Integer id, @RequestBody TipoComida comida) throws Exception {
		return tipoComidaService.existe(comida.getId()) ? ResponseUtil.success(tipoComidaService.guardar(comida))
				: ResponseUtil.badRequest("No existe la comida con el id especificado");
	}

	@GetMapping("/habilitado")
	public ResponseEntity<ApiResponse<List<TipoComida>>> buscarHabilitados() {
		List<TipoComida> tipoComidaHabilitadas = tipoComidaService.buscarHabilitado(true);
		return tipoComidaHabilitadas.isEmpty() ? ResponseUtil.notFound("No hay comidas habilitadas ")
				: ResponseUtil.success(tipoComidaHabilitadas);
	}

	@GetMapping("/deshabilitado")
	public ResponseEntity<ApiResponse<List<TipoComida>>> buscarDeshabilitados() {
		List<TipoComida> tipoComidaDeshabilitadas = tipoComidaService.buscarHabilitado(false);
		return tipoComidaDeshabilitadas.isEmpty() ? ResponseUtil.notFound("No hay comidas Deshabilitadas ")
				: ResponseUtil.success(tipoComidaDeshabilitadas);
	}

	@PutMapping("/habilitar/{id}")
	public ResponseEntity<ApiResponse<TipoComida>> habilitarComida(@PathVariable Integer id) throws Exception {
		TipoComida tipoComida = tipoComidaService.buscarPorId(id);

		if (tipoComida == null)
			return ResponseUtil.badRequest("El ID no existe");

		if (!tipoComida.isHabilitado()) {
			tipoComida.setHabilitado(true);
			return ResponseUtil.success(tipoComidaService.guardar(tipoComida));
		} else {
			return ResponseUtil.badRequest("La comida ya está habilitada.");
		}
	}

	@PutMapping("/deshabilitar/{id}")
	public ResponseEntity<ApiResponse<TipoComida>> deshabilitarComida(@PathVariable Integer id) throws Exception {
		TipoComida tipoComida = tipoComidaService.buscarPorId(id);

		if (tipoComida == null)
			return ResponseUtil.badRequest("El ID no existe");

		if (tipoComida.isHabilitado()) {
			tipoComida.setHabilitado(false);
			return ResponseUtil.success(tipoComidaService.guardar(tipoComida));
		} else {
			return ResponseUtil.badRequest("La comida ya está deshabilitada.");
		}
	}


	@DeleteMapping("/deshabilitado/{id}")
	public ResponseEntity<ApiResponse<Boolean>> eliminarComidaDeshabilitada(@PathVariable Integer id) throws Exception {
		TipoComida tipoComida = tipoComidaService.buscarPorId(id);

		if (tipoComida == null)
			return ResponseUtil.badRequest("El ID no existe");

		return !tipoComida.isHabilitado() ?
				ResponseUtil.success(tipoComidaService.eliminar(id)) :
				ResponseUtil.badRequest("No se puede eliminar. La comida no está deshabilitada.");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Boolean>> eliminarComida(@PathVariable Integer id) throws Exception {
		TipoComida tipoComida = tipoComidaService.buscarPorId(id);

		if (tipoComida == null)
			return ResponseUtil.badRequest("El ID no existe");

		return !tipoComida.isHabilitado() ?
				ResponseUtil.success(tipoComidaService.eliminar(id)) :
				ResponseUtil.badRequest("No se puede eliminar. La comida no está deshabilitada.");
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