package org.sistema.springmvc.forms.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.sistema.springmvc.forms.dao.GenericDAO;
import org.sistema.springmvc.forms.dao.ProviderDAO;
import org.sistema.springmvc.forms.models.City;
import org.sistema.springmvc.forms.models.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
public class ProviderController {
	private static final Logger logger = LoggerFactory
			.getLogger(ProviderController.class);

	@Autowired
	private ProviderDAO providerDAO;

	/**
	 * handles /provider/id
	 * 
	 * @param model
	 * @return the name of the view to show RequestMapping({"/cities/{id}"})
	 */
	@RequestMapping(method = RequestMethod.GET, value = { "/providers/{id}" })
	public String cityProviderDetail(@PathVariable(value = "id") Integer id,
			Map<String, Object> model) {
		logger.info("City provider detail");

		Provider provider = providerDAO.selectById(id, Provider.class);
		model.put("provider", provider);

		return "provider/providerDetail";
	}
	
	@RequestMapping(method = RequestMethod.GET, value = { "/providers/new" })
	public String newCity(Map<String, Object> model) {
		logger.info("Showing custom view GET ");

		
		
	
		List<City> cities = providerDAO.selectCities();	
		// add to model 
		model.put("provider", new Provider());
		model.put("cities", cities);

		return "provider/newProvider";
	}

	/**
	 * handles /cities/provider/new by POST
	 * 
	 * @return the name of the view to show RequestMapping({"/cities/new"})
	 */
	@RequestMapping(method = RequestMethod.POST, value = { "/providers/new" })
	public ModelAndView createProvider(@Valid Provider provider, BindingResult bindingResult) {
		
		
		
		ModelAndView modelAndView = new ModelAndView();

		
		if (bindingResult.hasErrors()) {
			modelAndView.setViewName("provider/newProvider");
			modelAndView.addObject("provider", provider);
			return modelAndView;
		}
	try{
		providerDAO.insert(provider);
			// We return view name
			modelAndView.setViewName("provider/created");
			modelAndView.addObject("provider", provider);
		
		
	} catch (Exception e){
		modelAndView.setViewName("error");
		modelAndView
				.addObject("error",
						"An error ocurred while trying to create a new provider. Please, try again");
		
	}
	return modelAndView;
	
	}

	/**
	 * Simply selects the update view for tasks
	 */
	@RequestMapping(value = "/providers/update/{id}", method = RequestMethod.GET)
	public String updateProvider(@PathVariable(value = "id") Integer providerId,
			Model model) {
		logger.info("Showing update provider view GET ");

		// We find the task through DAO and load into model
		model.addAttribute("provider", providerDAO.selectById(providerId,Provider.class));

		return "provider/update";
	}

	/**
	 * Handles the POST from the Custom.jsp page to update the City.
	 */
	@RequestMapping(value = "/providers/saveupdate", method = RequestMethod.POST)
	public ModelAndView saveUpdateProvider(@ModelAttribute("provider") @Valid Provider provider, BindingResult bindingResult) {
		logger.info("Save update provider " + provider.getId());
		ModelAndView modelAndView = new ModelAndView();

		
		if (bindingResult.hasErrors()) {
			logger.info(bindingResult.getAllErrors().toString());
			modelAndView.setViewName("provider/update");
			modelAndView.addObject("provider", provider);
			return modelAndView;
		}
	try{
		providerDAO.update(provider);
			// We return view name
			modelAndView.addObject("provider", provider);
			modelAndView.setViewName("provider/saveUpdated");
		
		
	} catch (Exception e){
		modelAndView.setViewName("error");
		modelAndView
				.addObject("error",
						"An error ocurred while trying to create a new provider. Please, try again");
		
	}
	return modelAndView;
	}

	/**
	 * Delete the specific task
	 */
	@RequestMapping(value = "/providers/delete/{id}", method = RequestMethod.GET)
	public String deleteProvider(@PathVariable(value = "id") Integer providerId,
			Model model) {
		logger.info("City detail /delete provider: " + providerId);

		// Store the concrete task to send it back before deleting to use it in
		// the following view.
		Provider provider = providerDAO.selectById(providerId, Provider.class);
		model.addAttribute("provider", provider);

		providerDAO.delete(provider);

		return "provider/deleted";
	}

}
