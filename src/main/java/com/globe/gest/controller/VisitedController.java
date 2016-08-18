package com.globe.gest.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.globe.gest.model.Audite;
import com.globe.gest.model.Gouvernorat;
import com.globe.gest.model.Localisation;
import com.globe.gest.model.Operator;
import com.globe.gest.model.Shops;
import com.globe.gest.model.User;
import com.globe.gest.model.Ville;
import com.globe.gest.model.Visite;
import com.globe.gest.service.AuditeService;
import com.globe.gest.service.GouvernoratService;
import com.globe.gest.service.LocalisationService;
import com.globe.gest.service.OperatorService;
import com.globe.gest.service.UserService;
import com.globe.gest.service.VilleService;
import com.globe.gest.service.VisiteService;

@Controller
@RequestMapping(value = "/visited")
@PreAuthorize("denyAll")
public class VisitedController {

	static Logger logger = LoggerFactory.getLogger(VisitedController.class);
	static String businessObject = "visite"; // used in RedirectAttributes
												// messages

	@Autowired
	private VisiteService visiteService;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private AuditeService auditeService;

	@Autowired
	private UserService userService;
	
	


	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	@PreAuthorize("hasRole('CTRL_USER_LIST_GET')")
	public String listShops(Model model) {
		logger.debug("IN: User/list-GET");

		List<Visite> visite = visiteService.getVisited();
		model.addAttribute("visite", visite);

		// if there was an error in /add, we do not want to overwrite
		// the existing user object containing the errors.
//		if (!model.containsAttribute("tovisitDTO")) {
//			logger.debug("Adding tovisitDTO object to model");
//			TovisitDTO tovisitDTO = new TovisitDTO();
//			model.addAttribute("tovisitDTO", tovisitDTO);
//		}
		return "visited-list";
	}

	


}
