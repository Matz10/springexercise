package org.sistema.springmvc.forms.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.sistema.springmvc.forms.dao.CityDAO;
import org.sistema.springmvc.forms.models.Provider;
import org.sistema.springmvc.forms.models.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller for users.
 * 
 * @author Eugenia Pérez Martínez
 *
 */
@Controller
public class CityController {
	private static final Logger logger = LoggerFactory
			.getLogger(CityController.class);

	@Autowired
	private CityDAO cityDAO;


	/**
	 * handles default /cities
	 * 
	 * @param model
	 * @return the name of the view to show RequestMapping({"/cities"})
	 */

	@RequestMapping(method = RequestMethod.GET, value = { "/", "/cities" })
	public String showCities(Map<String, Object> model) {
		logger.info("City showCities. ");

		
		List<City> cities = cityDAO.selectAll(City.class);
		model.put("cities", cities);

		return "city/cities";
	}

	/**
	 * handles default /cities/id
	 * 
	 * @param model
	 * @return the name of the view to show RequestMapping({"/cities/{id}"})
	 */
	@RequestMapping(method = RequestMethod.GET, value = { "/cities/{id}" })
	public String cityDetail(@PathVariable(value = "id") Integer id,
			Map<String, Object> model) {
		logger.info("City detail");

		City city = cityDAO.selectById(id, City.class);
		//The user gets his own collection of tasks load
		model.put("city", city);
		
		// We add task for the new task form
		Provider provider = new Provider();
		provider.setCity(city);
		model.put("provider", provider);

		return "city/cityDetail";
	}
	
	
	/**
	 * handles /cities/new by GET
	 * 
	 * @return the name of the view to show RequestMapping({"/cities/new"})
	 */
	@RequestMapping(method = RequestMethod.GET, value = { "/cities/new" })
	public String newCity(Map<String, Object> model) {
		logger.info("Showing custom view GET ");

		model.put("city", new City());

		return "city/newCity";
	}

	/**
	 * handles /cities/new by POST
	 * 
	 * @return the name of the view to show RequestMapping({"/cities/new"})
	 */
	@RequestMapping(method = RequestMethod.POST, value = { "/cities/new" })
	public ModelAndView createCity(@Valid City city, BindingResult bindingResult) {
		logger.info("Saveview POST " + city.getId());

		ModelAndView modelAndView = new ModelAndView();

	
			if (bindingResult.hasErrors()) {
				modelAndView.setViewName("city/newCity");
				modelAndView.addObject("city", city);
				return modelAndView;
			}
		try{
			cityDAO.insert(city);
				// We return view name
				modelAndView.setViewName("city/created");
				modelAndView.addObject("city", city);
			
			
		} catch (Exception e){
			modelAndView.setViewName("error");
			modelAndView
					.addObject("error",
							"An error ocurred while trying to create a new city. Please, try again");
			
		}
		return modelAndView;

	}

	
	
	
	/**
	 * Simply selects the update view
	 */
	@RequestMapping(value = "/cities/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable(value = "id") Integer cityId, Model model) {
		logger.info("Showing update view GET ");

		// We find the user through DAO and load into model
		model.addAttribute("city", cityDAO.selectById(cityId, City.class));

		return "city/update";
	}

	/**
	 * Handles the POST from the Custom.jsp page to update the City.
	 */
	@RequestMapping(value = "/cities/saveupdate", method = RequestMethod.POST)
	public ModelAndView saveUpdate(@Valid City city, BindingResult bindingResult) {
		logger.info("Save update " + city.getId());
		ModelAndView modelAndView = new ModelAndView();

		
		if (bindingResult.hasErrors()) {
			logger.info(bindingResult.getAllErrors().toString());
			modelAndView.setViewName("city/update");
			modelAndView.addObject("city", city);
			return modelAndView;
		}
	try{
		cityDAO.update(city);
			// We return view name
			modelAndView.setViewName("city/saveUpdated");
			modelAndView.addObject("city", city);
		
		
	} catch (Exception e){
		modelAndView.setViewName("error");
		modelAndView
				.addObject("error",
						"An error ocurred while trying to create a new city. Please, try again");
		
	}
	return modelAndView;
	}
	
	

	/**
	 * Delete the specific city
	 */
	@RequestMapping(value = "/cities/delete/{id}", method = RequestMethod.GET)
	public String delete(@PathVariable(value = "id") Integer cityId, Model model) {
		logger.info("City detail /delete");
		
		cityDAO.delete(cityDAO.selectById(cityId, City.class));
		model.addAttribute("cityId", cityId);

		return "city/deleted";
	}
	

}
