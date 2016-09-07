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
@RequestMapping(value = "/tovisit")
@PreAuthorize("denyAll")
public class TovisitController {

	static Logger logger = LoggerFactory.getLogger(TovisitController.class);
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
	
	@RequestMapping(value = "/user")
	@PreAuthorize("hasRole('CTRL_TOVISIT_LIST_GET')")
	@ResponseBody
	public int getUser() {
		Object s= SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = (User) s;
		return user.getId();
	}

	@ModelAttribute("allAuditors")
	@PreAuthorize("hasAnyRole('CTRL_TOVISIT_LIST_GET','CTRL_TOVISIT_EDIT_GET')")
	public List<User> getAllAuditors() {
		
		return userService.getAuditors();
	}
	
	@ModelAttribute("allAudites")
	@PreAuthorize("hasAnyRole('CTRL_TOVISIT_LIST_GET','CTRL_TOVISIT_EDIT_GET')")
	public List<Audite> getAllAudites() {
		
		return auditeService.getAudite();
	}
	
	@RequestMapping(value = "/test")
	@PreAuthorize("hasRole('CTRL_TOVISIT_LIST_GET')")
	@ResponseBody
	public String getTest() {

		System.out.println("****************");
		Object s= SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = (User) s;
		return user.getPhone();
	}
	


	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	@PreAuthorize("hasRole('CTRL_TOVISIT_LIST_GET')")
	public String listShops(Model model) {
		logger.debug("IN: User/list-GET");

		List<Visite> visite = visiteService.getVisite();
		model.addAttribute("visite", visite);

		// if there was an error in /add, we do not want to overwrite
		// the existing user object containing the errors.
		if (!model.containsAttribute("tovisitDTO")) {
			logger.debug("Adding tovisitDTO object to model");
			TovisitDTO tovisitDTO = new TovisitDTO();
			model.addAttribute("tovisitDTO", tovisitDTO);
		}
		return "tovisit-list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@PreAuthorize("hasRole('CTRL_TOVISIT_ADD_POST')")
	public String addVisit(@Valid @ModelAttribute TovisitDTO tovisitDTO, BindingResult result,
			RedirectAttributes redirectAttrs) {

		logger.debug("IN: Tovisit/add-POST");
System.out.println("************************22");
System.out.println(tovisitDTO.getId_auditor());
		if (result.hasErrors()) {
			logger.debug("TovisitDTO add error: " + result.toString());
			redirectAttrs.addFlashAttribute("org.springframework.validation.BindingResult.tovisitDTO", result);
			redirectAttrs.addFlashAttribute("tovisitDTO", tovisitDTO);
			return "redirect:/tovisit/list";
		} else {
			Visite visite = new Visite();
			visite = getVisite(tovisitDTO);
			visiteService.addVisite(visite);
			return "redirect:/tovisit/list";
		}
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	@PreAuthorize("hasRole('CTRL_TOVISIT_EDIT_GET')")
	public String editToVisitPage(@RequestParam(value = "id", required = true) Integer id, Model model,
			RedirectAttributes redirectAttrs) {

		logger.debug("IN: Shops/edit-GET:  ID to query = " + id);

		if (!model.containsAttribute("tovisitDTO")) {
			logger.debug("Adding tovisitDTO object to model");
			Visite visite = visiteService.getVisite(id);
			TovisitDTO tovisitDTO = getVisiteDTO(visite);
			logger.debug("Shops/edit-GET:  " + tovisitDTO.toString());
			model.addAttribute("tovisitDTO", tovisitDTO);
		}
		return "tovisit-edit";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@PreAuthorize("hasRole('CTRL_TOVISIT_EDIT_POST')")
	public String editVisite(@Valid @ModelAttribute TovisitDTO tovisitDTO, BindingResult result,
			RedirectAttributes redirectAttrs, @RequestParam(value = "action", required = true) String action) {

		logger.debug("IN: Shops/edit-POST: " + action);

		if (action.equals(messageSource.getMessage("button.action.cancel", null, Locale.US))) {
			// String message =
			// messageSource.getMessage("ctrl.message.success.cancel",
			// new Object[] {"Edit", businessObject,
			// shopsDTO.getShopsname()}, Locale.US);
			// redirectAttrs.addFlashAttribute("message", message);
		} else if (result.hasErrors()) {
			logger.debug("Shops-edit error: " + result.toString());
			redirectAttrs.addFlashAttribute("org.springframework.validation.BindingResult.tovisitDTO", result);
			redirectAttrs.addFlashAttribute("tovisitDTO", tovisitDTO);
			return "redirect:/tovisit/edit?id=" + tovisitDTO.getId_visite();
		} else if (action.equals(messageSource.getMessage("button.action.save", null, Locale.US))) {
			logger.debug("tovisit/edit-POST:  " + tovisitDTO.toString());
			Visite visite = getVisite(tovisitDTO);
			visiteService.updateVisite(visite);
		}
		return "redirect:/tovisit/list";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	@PreAuthorize("hasRole('CTRL_TOVISIT_DELETE_GET')")
	public String deleteToVisit(@RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "phase", required = true) String phase, Model model,
			RedirectAttributes redirectAttrs) {

		Visite visit;
		visit = visiteService.getVisite(id);

		logger.debug("IN: Shops/delete-GET | id = " + id + " | phase = " + phase + " | " + visit.toString());

		if (phase.equals(messageSource.getMessage("button.action.cancel", null, Locale.US))) {
			// String message =
			// messageSource.getMessage("ctrl.message.success.cancel",
			// new Object[] {"Delete", businessObject,
			// shops.getShopsname()}, Locale.US);
			// redirectAttrs.addFlashAttribute("message", message);
			return "redirect:/tovisit/list";
		} else if (phase.equals(messageSource.getMessage("button.action.stage", null, Locale.US))) {
			logger.debug("     adding shops : " + visit.toString());
			model.addAttribute("visite", visit);
			return "tovisit-delete";
		} else if (phase.equals(messageSource.getMessage("button.action.delete", null, Locale.US))) {
			visiteService.deleteVisite(visit.getId_visite());
			// String message =
			// messageSource.getMessage("ctrl.message.success.delete",
			// new Object[] {businessObject, shops.getShopsname()},
			// Locale.US);
			// redirectAttrs.addFlashAttribute("message", message);
			return "redirect:/tovisit/list";
		}

		return "redirect:/tovisit/list";
	}
//
	@PreAuthorize("hasAnyRole('CTRL_TOVISIT_EDIT_GET','CTRL_TOVISIT_DELETE_GET')")
	public TovisitDTO getVisiteDTO(Visite visite) {
		TovisitDTO tovisitDTO = new TovisitDTO();
		tovisitDTO.setId_visite(visite.getId_visite());
		tovisitDTO.setDate_a_visiter(visite.getDate_a_visiter());
		tovisitDTO.setDate_visite(visite.getDate_visite());
		tovisitDTO.setNote(visite.getNote());
		Audite audite = new Audite();
		audite = visite.getAudite();
		tovisitDTO.setID_AUDITE(audite.getID_AUDITE());
		
		User user = new User();
		user = visite.getUser();
		tovisitDTO.setId_user(user.getId());
		
		User auditor = new User();
		auditor = visite.getAuditor();
		tovisitDTO.setId_auditor(auditor.getId());
		
		return tovisitDTO;
	}

	@PreAuthorize("hasAnyRole('CTRL_TOVISIT_ADD_POST','CTRL_TOVISIT_EDIT_POST')")
	public Visite getVisite(TovisitDTO tovisitDTO) {
		Visite visite = new Visite();
		visite.setId_visite(tovisitDTO.getId_visite());
		visite.setDate_a_visiter(tovisitDTO.getDate_a_visiter());
		visite.setDate_visite(tovisitDTO.getDate_visite());
		visite.setNote(tovisitDTO.getNote());
		
		Audite audite = new Audite();
		audite = auditeService.getAudite(tovisitDTO.getID_AUDITE());
		visite.setAudite(audite);
		
		User user = new User();
		user = userService.getUser(tovisitDTO.getId_user());
		visite.setUser(user);
		
		User auditor = new User();
		auditor = userService.getUser(tovisitDTO.getId_auditor());
		visite.setAuditor(auditor);


		return visite;
	}
//	
//	
//	
//	@RequestMapping(value = {"/", "/search"}, method = RequestMethod.GET)
//    @PreAuthorize("hasRole('CTRL_TOVISIT_LIST_GET')")
//    public String searchUsers(@RequestParam(value = "nom_audite", required = false)
//    String nom_audite,
//    @RequestParam(value = "stype", required = false) String stype,
//    @RequestParam(value = "operator", required = false) String operator,
//    @RequestParam(value = "Governorate", required = false) String gouvernorat,
//    @RequestParam(value = "Ville", required = false) String ville,
//    @RequestParam(value = "Localisation", required = false) String localisation,
//    Model model, RedirectAttributes redirectAttrs) {
//        logger.debug("IN: Shops/list-GET");
//
//        List<Shops> shops = shopsService.getShopsByName(nom_audite,stype,operator,gouvernorat,ville,localisation);
//        model.addAttribute("shops", shops);
//
//        // if there was an error in /add, we do not want to overwrite
//        // the existing user object containing the errors.
//        if (!model.containsAttribute("shopsDTO")) {
//            logger.debug("Adding UserDTO object to model");
//            ShopsDTO shopsDTO = new ShopsDTO();
//            model.addAttribute("shopsDTO", shopsDTO);
//        }
//        return "shops-list";
//    }


}
