package com.globe.gest.controller;

import java.security.Principal;
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

import com.globe.gest.model.Gouvernorat;
import com.globe.gest.model.Localisation;
import com.globe.gest.model.Operator;
import com.globe.gest.model.Shops;
import com.globe.gest.model.User;
import com.globe.gest.model.Ville;
import com.globe.gest.service.GouvernoratService;
import com.globe.gest.service.LocalisationService;
import com.globe.gest.service.OperatorService;
import com.globe.gest.service.ShopsService;
import com.globe.gest.service.VilleService;

@Controller
@RequestMapping(value = "/shops")
@PreAuthorize("denyAll")
public class ShopsController {

	static Logger logger = LoggerFactory.getLogger(ShopsController.class);
	static String businessObject = "audite"; // used in RedirectAttributes
												// messages

	@Autowired
	private ShopsService shopsService;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private OperatorService operatorService;

	@Autowired
	private LocalisationService localisationService;

	@Autowired
	private VilleService villeService;

	@Autowired
	private GouvernoratService gouvernoratService;
	
	@RequestMapping(value = "/test")
	@PreAuthorize("hasRole('CTRL_USER_LIST_GET')")
	@ResponseBody
	public String getTest() {

		System.out.println("****************");
		Object s= SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = (User) s;
		return user.getPhone();
	}
	

	@RequestMapping(value = "/ville")
	@PreAuthorize("hasRole('CTRL_USER_LIST_GET')")
	@ResponseBody
	public Set<String> getVille(int gouvernorat) {
		Set<String> set = new HashSet<String>();
		List<Ville>  list= villeService.getVille(gouvernorat);
		for(Ville i:list){
			set.add(i.getNom_Ville());
		}
		return  set;
	}
	
	@RequestMapping(value = "/ville1")
	@PreAuthorize("hasRole('CTRL_USER_LIST_GET')")
	@ResponseBody
	public Set<String> getVilles(String gouvernorat) {
		Set<String> set = new HashSet<String>();
		int id= gouvernoratService.getGouvernorat(gouvernorat);
		List<Ville>  list= villeService.getVille(id);
		for(Ville i:list){
			set.add(i.getNom_Ville());
		}
		return  set;
	}

	
	@RequestMapping(value = "/localisation")
	@PreAuthorize("hasRole('CTRL_USER_LIST_GET')")
	@ResponseBody
	public Set<String> getLocalisation(String ville) {
		Set<String> set = new HashSet<String>();
		int id = villeService.getVille(ville);
		List<Localisation>  list= localisationService.getLocalisations(id);
		for(Localisation i:list){
			set.add(i.getNom_Loc());
		}	
		return  set;
	}
	
	
	@RequestMapping(value = "/loc")
	@PreAuthorize("hasRole('CTRL_USER_LIST_GET')")
	@ResponseBody
	public int getLoc(String localisation) {
		int id = localisationService.getLocalisation(localisation);
		return id;
	}
	
	@ModelAttribute("allOperators")
	@PreAuthorize("hasAnyRole('CTRL_USER_LIST_GET','CTRL_USER_EDIT_GET')")
	public List<Operator> getAllOperators() {
		
		return operatorService.getOperators();
	}

	@ModelAttribute("allGouvernorat")
	@PreAuthorize("hasAnyRole('CTRL_USER_LIST_GET','CTRL_USER_EDIT_GET')")
	public List<Gouvernorat> getAllGouvernorat() {
		return gouvernoratService.getGouvernorat();
	}

	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	@PreAuthorize("hasRole('CTRL_USER_LIST_GET')")
	public String listShops(Model model) {
		logger.debug("IN: User/list-GET");

		List<Shops> shops = shopsService.getShops();
		model.addAttribute("shops", shops);

		// if there was an error in /add, we do not want to overwrite
		// the existing user object containing the errors.
		if (!model.containsAttribute("auditeDTO")) {
			logger.debug("Adding shopsDTO object to model");
			ShopsDTO shopsDTO = new ShopsDTO();
			model.addAttribute("shopsDTO", shopsDTO);
		}
		return "shops-list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@PreAuthorize("hasRole('CTRL_USER_ADD_POST')")
	public String addShops(@Valid @ModelAttribute ShopsDTO shopsDTO, BindingResult result,
			RedirectAttributes redirectAttrs) {

		logger.debug("IN: Shops/add-POST");

		if (result.hasErrors()) {
			logger.debug("ShopsDTO add error: " + result.toString());
			redirectAttrs.addFlashAttribute("org.springframework.validation.BindingResult.shopsDTO", result);
			redirectAttrs.addFlashAttribute("shopsDTO", shopsDTO);
			return "redirect:/shops/list";
		} else {
			Shops shops = new Shops();
			shops = getShops(shopsDTO);
			shopsService.addShops(shops);
			return "redirect:/shops/list";
		}
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	@PreAuthorize("hasRole('CTRL_USER_EDIT_GET')")
	public String editShopsPage(@RequestParam(value = "id", required = true) Integer id, Model model,
			RedirectAttributes redirectAttrs) {

		logger.debug("IN: Shops/edit-GET:  ID to query = " + id);

		if (!model.containsAttribute("shopsDTO")) {
			logger.debug("Adding shopsDTO object to model");
			Shops shops = shopsService.getShops(id);
			ShopsDTO shopsDTO = getShopsDTO(shops);
			logger.debug("Shops/edit-GET:  " + shopsDTO.toString());
			model.addAttribute("shopsDTO", shopsDTO);
		}
		return "shops-edit";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@PreAuthorize("hasRole('CTRL_USER_EDIT_POST')")
	public String editShops(@Valid @ModelAttribute ShopsDTO shopsDTO, BindingResult result,
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
			redirectAttrs.addFlashAttribute("org.springframework.validation.BindingResult.shopsDTO", result);
			redirectAttrs.addFlashAttribute("shopsDTO", shopsDTO);
			return "redirect:/shops/edit?id=" + shopsDTO.getID_AUDITE();
		} else if (action.equals(messageSource.getMessage("button.action.save", null, Locale.US))) {
			logger.debug("Shops/edit-POST:  " + shopsDTO.toString());
			Shops shops = getShops(shopsDTO);
			shopsService.updateShops(shops);
		}
		return "redirect:/shops/list";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	@PreAuthorize("hasRole('CTRL_USER_DELETE_GET')")
	public String deleteShops(@RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "phase", required = true) String phase, Model model,
			RedirectAttributes redirectAttrs) {

		Shops shops;
		shops = shopsService.getShops(id);

		logger.debug("IN: Shops/delete-GET | id = " + id + " | phase = " + phase + " | " + shops.toString());

		if (phase.equals(messageSource.getMessage("button.action.cancel", null, Locale.US))) {
			// String message =
			// messageSource.getMessage("ctrl.message.success.cancel",
			// new Object[] {"Delete", businessObject,
			// shops.getShopsname()}, Locale.US);
			// redirectAttrs.addFlashAttribute("message", message);
			return "redirect:/shops/list";
		} else if (phase.equals(messageSource.getMessage("button.action.stage", null, Locale.US))) {
			logger.debug("     adding shops : " + shops.toString());
			model.addAttribute("shops", shops);
			return "shops-delete";
		} else if (phase.equals(messageSource.getMessage("button.action.delete", null, Locale.US))) {
			shopsService.deleteShops(shops.getID_AUDITE());
			// String message =
			// messageSource.getMessage("ctrl.message.success.delete",
			// new Object[] {businessObject, shops.getShopsname()},
			// Locale.US);
			// redirectAttrs.addFlashAttribute("message", message);
			return "redirect:/shops/list";
		}

		return "redirect:/shops/list";
	}

	@PreAuthorize("hasAnyRole('CTRL_USER_EDIT_GET','CTRL_USER_DELETE_GET')")
	public ShopsDTO getShopsDTO(Shops shops) {
		ShopsDTO shopsDTO = new ShopsDTO();
		shopsDTO.setID_AUDITE(shops.getID_AUDITE());
		shopsDTO.setNom_audite(shops.getNom_audite());
		shopsDTO.setDtype(shops.getDtype());
		shopsDTO.setIsValid(shops.getIsValid());
		shopsDTO.setLatitude_boutique(shops.getLatitude_boutique());
		shopsDTO.setLongitude_boutique(shops.getLongitude_boutique());
		shopsDTO.setAdresse_boutique(shops.getAdresse_boutique());
		shopsDTO.setPhone_boutique(shops.getPhone_boutique());
		shopsDTO.setStype(shops.getStype());
		Operator operator = new Operator();
		operator = shops.getOperator();
		shopsDTO.setID_OP(operator.getID_OP());
		Localisation localisation = new Localisation();
		localisation = shops.getLocalisation();
		shopsDTO.setID_LOC(localisation.getID_LOC());
		return shopsDTO;
	}

	@PreAuthorize("hasAnyRole('CTRL_USER_ADD_POST','CTRL_USER_EDIT_POST')")
	public Shops getShops(ShopsDTO shopsDTO) {
		Shops shops = new Shops();
		shops.setID_AUDITE(shopsDTO.getID_AUDITE());
		shops.setNom_audite(shopsDTO.getNom_audite());
		shops.setDtype(shopsDTO.getDtype());
		shops.setIsValid(shopsDTO.getIsValid());
		shops.setLatitude_boutique(shopsDTO.getLatitude_boutique());
		shops.setLongitude_boutique(shopsDTO.getLongitude_boutique());
		shops.setAdresse_boutique(shopsDTO.getAdresse_boutique());
		shops.setPhone_boutique(shopsDTO.getPhone_boutique());
		shops.setStype(shopsDTO.getStype());
		Operator operator = new Operator();
		operator = operatorService.getOperator(shopsDTO.getID_OP());
		shops.setOperator(operator);
		Localisation localisation = new Localisation();
		localisation = localisationService.getLocalisation(shopsDTO.getID_LOC());
		shops.setLocalisation(localisation);

		return shops;
	}
	
	
	
	@RequestMapping(value = {"/", "/search"}, method = RequestMethod.GET)
    @PreAuthorize("hasRole('CTRL_USER_LIST_GET')")
    public String searchUsers(@RequestParam(value = "nom_audite", required = false)
    String nom_audite,
    @RequestParam(value = "stype", required = false) String stype,
    @RequestParam(value = "operator", required = false) String operator,
    @RequestParam(value = "Governorate", required = false) String gouvernorat,
    @RequestParam(value = "Ville", required = false) String ville,
    @RequestParam(value = "Localisation", required = false) String localisation,
    Model model, RedirectAttributes redirectAttrs) {
        logger.debug("IN: Shops/list-GET");

        List<Shops> shops = shopsService.getShopsByName(nom_audite,stype,operator,gouvernorat,ville,localisation);
        model.addAttribute("shops", shops);

        // if there was an error in /add, we do not want to overwrite
        // the existing user object containing the errors.
        if (!model.containsAttribute("shopsDTO")) {
            logger.debug("Adding UserDTO object to model");
            ShopsDTO shopsDTO = new ShopsDTO();
            model.addAttribute("shopsDTO", shopsDTO);
        }
        return "shops-list";
    }


}
