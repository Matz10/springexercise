package org.sistema.springmvc.forms.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Represents a Provider.
 * 
 * @author Eugenia Pérez Martínez
 *
 */
@Entity
public class Provider {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotEmpty(message="No vacio")
	@NotNull(message="No vacio")
	private String nombre;
	@NotEmpty(message="No vacio")
	@NotNull(message="No vacio")
	private String direccion;
	@NotEmpty(message="No vacio")
	@NotNull(message="No vacio")
	private String telefono;
	@NotEmpty(message="No vacio")
	@NotNull(message="No vacio")
	private String email;
	@ManyToOne
    @JoinColumn(name="idcity")
	private City city;
	
	/**
	 * default constructor
	 */
	public Provider () {
		
	}
	
	/**
	 * @param id
	 * @param name
	 * @param direccion
	 * @param city
	 */
	public Provider(int id, String nombre, String direccion, String telefono, String email, City city) {
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono=telefono;
		this.email=email;
		this.city = city;
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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the city
	 */
	public City getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(City city) {
		this.city = city;
	}

	

}
