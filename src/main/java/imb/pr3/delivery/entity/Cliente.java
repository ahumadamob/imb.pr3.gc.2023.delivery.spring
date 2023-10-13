package imb.pr3.delivery.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

/* Se inicia colocando a la clase Cliente como una entidad, esto para a posterior
   utlizar los metodos CRUD para interactuar con la tabla Cliente en la base de datos. */

@Entity
public class Cliente {
	
	/*Se identifica al atributo id como la clave primaria para la tabla Cliente.
	 A través de la anotación GeneratedValue automatizamos para que los valores que tome id
	 se generen e incrementen de forma automatica, uno en uno*/
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Integer id;
	
	/*Para los demás atributos, simplemente se definen y utlizan la anotación NotBlank 
	 para especificar que los valores no pueden ser ni nulos, ni vacíos. En caso de ser así,
	 se mostrarán los mensajes que están definidos entre ()*/
	@NotBlank(message= "El nombre no puede estar vacío")
	private String nombre;
	
	@NotBlank(message= "El apellido no puede estar vacío")
	private String apellido;
	
	@NotBlank(message= "El telefono no puede estar vacío")
	private String telefono;
	
	/*Se genera una relación de uno a muchos entre las entidades Cliente y Pedido. 
	 La lista, se debe justamente al hecho de que un Cliente puede tener muchos Pedidos.*/
	@OneToMany(mappedBy = "cliente")
	private List<Pedido> pedidos;
	
	
	/*Constructor predeterminado 
	No toma ningún argumento y no realiza ninguna acción específica. Se incluye para permitir la 
	creación de instancias de la clase Cliente sin inicialización adicional en el momento de la creación.*/
	public Cliente() {

	}
	
	/*Constructor sobrecargado 
	Este es un constructor que acepta cuatro parámetros: 
	id (de tipo Integer), nombre, apellido y telefono (todos de tipo String).
	Se utiliza para crear una instancia de Cliente con datos específicos pasados como argumentos.
	Dentro del constructor, se asignan los valores de los parámetros a los correspondientes.*/
	
	public Cliente(Integer id, String nombre, String apellido, String telefono) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.telefono = telefono;
	}
	
	/*
	 * Getter (getParameter()):
	Este es un método de acceso (getter) para los distintos atributos que contiene la clase Cliente.
	Devuelven el valor actual del atributo.
	Los métodos usados son los siguientes:
	public Integer getId(); -> Obtiene el ID
	public String getNombre(); -> Obtiene el Nombre
	public String getApellido(); -> Obtiene el Apellido
	public String getTelefono(); -> Obtiene el Telefono
	
	Setter (setParameter(Parameter id)):
	Este es un método de modificación (setter) para el atributo que se especifique.
	Permite establecer un nuevo valor.
	
	Los métodos usados son los siguientes:
	public void setId(Integer id){ this.id = id } -> Actualiza el valor del atributo id
	public void setNombre(String nombre) { this.nombre = nombre } -> Actualiza el valor del atributo nombre
	public void setApellido(String apellido) { this.apellido = apellido } -> Actualiza el valor del atributo apellido
	public void setTelefono(String telefono) { this.telefono = telefono } -> Actualiza el valor del atributo telefono
	*/
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	
}
