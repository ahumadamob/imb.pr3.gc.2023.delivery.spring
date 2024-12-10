package imb.pr3.delivery.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Comida {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	@NotBlank(message = " El nombre de la comida no puede estar vacío")
	String nombre;
	double precio;

	@ManyToOne
	@JoinColumn(name = "tipo-comida")
	TipoComida tipocomida;

	public Comida() {
	}
    
	

	public Comida(int id, @NotBlank(message = " El nombre de la comida no puede estar vacío") String nombre,
			double precio) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precio = precio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public TipoComida getTipocomida() {
		return tipocomida;
	}

	public void setTipocomida(TipoComida tipocomida) {
		this.tipocomida = tipocomida;
	}

	
     
}
