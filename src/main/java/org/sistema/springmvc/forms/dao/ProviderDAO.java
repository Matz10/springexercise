/**
 * 
 */
package org.sistema.springmvc.forms.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.sistema.springmvc.forms.models.City;
import org.sistema.springmvc.forms.models.Provider;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * implementation of ProductDAO
 * 
 * @author Eugenia Pérez Martínez
 * @email eugenia_perez@cuatrovientos.org
 *
 */

public class ProviderDAO extends GenericDAO<Provider> {


	/**
	 * Selects all product types by name
	 * 
	 * @return List of products
	 */
	@Transactional(readOnly = true)
	public List<City >selectCities() {

		List<City> cities = null;

			Query query = getSession()
							.createQuery("from City");
		    
			// We get a generic object list
			cities = query.list();

		return cities;
	}
	

}
