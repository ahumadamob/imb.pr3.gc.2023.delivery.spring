package imb.progra.delivery.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import imb.progra.delivery.entity.Estado;
import imb.progra.delivery.service.EstadoInterfaz;

@RestController
@RequestMapping("api/v1")

public class Controlador {
	@Autowired    
	EstadoInterfaz servicio ;
	
	@GetMapping("/estado")
	public List<Estado> procesaElget(){
		return servicio.obtenerTodas();
		
		
	}
	@PostMapping("/estado")
	public Estado crear(@RequestBody Estado estado) {
	    servicio.guardarEstado(estado);
		return estado;

	}
	@GetMapping("/estado/{id}")
	public Estado buscarPorId(@PathVariable("id")Integer id) {
		return servicio.buscar(id);
	}
	@PutMapping("/estados")
	public Estado modificar(@RequestBody Estado estado) {
		servicio.guardarEstado(estado);
		return estado;

	}
	@DeleteMapping("/estado/{id}")
	public void eliminar(@PathVariable("id") Integer id) {
		servicio.eliminarEstado(id);

	}

}
