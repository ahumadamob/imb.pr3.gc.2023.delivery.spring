package imb.pr3.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import imb.pr3.delivery.entity.Cliente;


/* 
 	Se trata de una interfaz llamada ClienteRepository que extiende de JpaRepository.
  	Extendemos de JpaRepository para que nos proporcione métodos CRUD (Crear, Leer, Actualizar, Eliminar) 
  	para la entidad especificada. 		
  	JpaRepository<Cliente,Integer>
	    -> Cliente es la clase de la entidad para la cual se proporcionarán operaciones CRUD.
		-> Integer es el tipo del identificador de la entidad (id).
	
 	Al extender JpaRepository, obtenemos métodos como save, findById, delete, y otros para 
 	realizar operaciones de persistencia en la base de datos asociada a la entidad Cliente.
 */
public interface ClienteRepository extends JpaRepository<Cliente,Integer> {

}
