/**
 * 
 */
package org.sistema.springmvc.forms.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.sistema.springmvc.forms.models.City;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * implementation of ProductDAO
 * 
 * @author Eugenia Pérez Martínez
 * @email eugenia_perez@cuatrovientos.org
 *
 */

public class CityDAO extends GenericDAO<City> {


		/**
	 * Selects all product types by name
	 * 
	 * @return List of products
	 */
	@Transactional(readOnly = true)
	public List<City>selectByName(String nombre) {

		List<City> cities = null;

			Query query = getSession()
							.createQuery("from City u where u.nombre LIKE :nombre");
		    query.setParameter("nombre", "%"+nombre+"%");
		    
			// We get a generic object list
			cities = query.list();

		return cities;
	}

}
