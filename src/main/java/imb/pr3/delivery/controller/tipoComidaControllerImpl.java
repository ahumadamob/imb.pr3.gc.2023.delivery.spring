package imb.pr3.delivery.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import imb.pr3.delivery.entity.tipoComida;
import imb.pr3.delivery.service.jpa.tipoComidaServiceImpl;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path="api/v1/tipoComida")
public class tipoComidaControllerImpl {
	
	@Autowired
	private tipoComidaServiceImpl tipoComidaServiceImpl;

	@GetMapping("")
	public ResponseEntity<ApiResponce<List<tipoComida>>>buscarTodo(){
		ApiResponce<List<tipoComida>> response= new ApiResponce<List<tipoComida>>(0, null, tipoComidaServiceImpl.buscartodo());
        return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponce<tipoComida>> buscarPorId(@PathVariable("id") Long id){
		if(this.existe(id)) {
			tipoComida comida = tipoComidaServiceImpl.buscarPorId(id);
			ApiResponce<tipoComida> response = new ApiResponce<tipoComida>(HttpStatus.OK.value(), null, comida);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}else {
			List<String> messages = new ArrayList<>();
			messages.add("No se encontró el producto con id = " + id.toString());
			ApiResponce<tipoComida> response = new ApiResponce<tipoComida>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}	
	}
	@PostMapping("")
	public ResponseEntity<ApiResponce<tipoComida>> guardar(@RequestBody tipoComida comida) {
		if(this.existe(comida.getId())) {
			List<String> messages = new ArrayList<>();
			messages.add("Ya existe una comida con el ID = " + comida.getId().toString());
			messages.add("Para actualizar utilice el verbo PUT");
			ApiResponce<tipoComida> response = new ApiResponce<tipoComida>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}else {
			tipoComidaServiceImpl.guardar(comida);
			ApiResponce<tipoComida> response = new ApiResponce<tipoComida>(HttpStatus.CREATED.value(), null, comida);
			return ResponseEntity.status(HttpStatus.CREATED).body(response);			
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponce<tipoComida>> modificar(@PathVariable("id") Long id,@RequestBody tipoComida comida) {
		if(this.existe(id)) {
			tipoComidaServiceImpl.modificar(id, comida);
			ApiResponce<tipoComida>response = new ApiResponce<tipoComida>(HttpStatus.OK.value(),null,comida);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		}else {
			List<String> messages = new ArrayList<>();
			messages.add("No se encontró el producto = " + id.toString());
			ApiResponce<tipoComida> response = new ApiResponce<tipoComida>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}	
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponce<tipoComida>> borrar(@PathVariable("id") Long id) {
		if(this.existe(id)) {
			tipoComidaServiceImpl.borrar(id);
			List<String> messages = new ArrayList<>();
			messages.add("El producto fue borrado");
			ApiResponce<tipoComida>response = new ApiResponce<tipoComida>(HttpStatus.OK.value(),messages,null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}else {
			List<String> messages = new ArrayList<>();
			messages.add("No existe el producto = " + id.toString());
			ApiResponce<tipoComida> response = new ApiResponce<tipoComida>(HttpStatus.BAD_REQUEST.value(), messages, null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}	
	}
	
	private boolean existe(Long id) {
		if(id == null) {
			return false;
		}else{
			tipoComida comida = tipoComidaServiceImpl.buscarPorId(id);
			if(comida == null) {
				return false;				
			}else {
				return true;
			}
		}
}
}
