package org.sistema.springmvc.forms.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Represents a user.
 * 
 * @author Eugenia Pérez Martínez
 *
 */
@Entity
public class City {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotNull(message="Enter a codigo postal")
	@NotEmpty(message="Enter a name")
	private String nombre;
	@Min(value=10000, message="valor minimo 10000")
	@Max(value=99999,message="valor maximo 99999")
	@NotNull(message="Enter a codigo postal")
	private Integer cpostal;
//	@Min(value=1, message="valor minimo 1")
//	@NotNull(message="Enter a id provincia")
//	private Integer idprovincia;
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER, mappedBy="city")
	private Set<Provider> providers = new HashSet<Provider>();

	/**
	 * default constructor
	 */
	public City() {
	}

	/**
	 * constructor with parameters
	 * @param id
	 * @param nombre
	 * @param cpostal
	 * @param idprovincia
	 */
	public City(int id, String nombre, Integer cpostal) {
		this.id = id;
		this.nombre = nombre;
		this.cpostal = cpostal;
//		this.idprovincia = idprovincia;
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

	public Integer getCpostal() {
		return cpostal;
	}

	public void setCpostal(Integer cpostal) {
		this.cpostal = cpostal;
	}

//	public Integer getIdprovincia() {
//		return idprovincia;
//	}
//
//	public void setIdprovincia(Integer idprovincia) {
//		this.idprovincia = idprovincia;
//	}

	/**
	 * @return the providers
	 */
	public Set<Provider> getProviders() {
		return providers;
	}

	/**
	 * @param providers the providers to set
	 */
	public void setProviders(Set<Provider> providers) {
		this.providers = providers;
	}

}
