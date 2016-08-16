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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.globe.gest.model.Camion;
import com.globe.gest.model.Gouvernorat;
import com.globe.gest.model.Localisation;
import com.globe.gest.model.Operator;
import com.globe.gest.model.Ville;
import com.globe.gest.service.CamionService;
import com.globe.gest.service.GouvernoratService;
import com.globe.gest.service.LocalisationService;
import com.globe.gest.service.OperatorService;
import com.globe.gest.service.VilleService;

@Controller
@RequestMapping(value = "/camion")
@PreAuthorize("denyAll")
public class CamionController {

	static Logger logger = LoggerFactory.getLogger(CamionController.class);
	static String businessObject = "camion"; // used in RedirectAttributes
												// messages

	@Autowired
	private CamionService camionService;

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

	@RequestMapping(value = "/ville")
	@PreAuthorize("hasRole('CTRL_USER_LIST_GET')")
	@ResponseBody
	public Set<String> getVille(int gouvernorat) {
		Set<String> set = new HashSet<String>();
		List<Ville> list = villeService.getVille(gouvernorat);
		for (Ville i : list) {
			set.add(i.getNom_Ville());
		}
		return set;
	}

	@RequestMapping(value = "/ville1")
	@PreAuthorize("hasRole('CTRL_USER_LIST_GET')")
	@ResponseBody
	public Set<String> getVilles(String gouvernorat) {
		Set<String> set = new HashSet<String>();
		int id = gouvernoratService.getGouvernorat(gouvernorat);
		List<Ville> list = villeService.getVille(id);
		for (Ville i : list) {
			set.add(i.getNom_Ville());
		}
		return set;
	}

	@RequestMapping(value = "/localisation")
	@PreAuthorize("hasRole('CTRL_USER_LIST_GET')")
	@ResponseBody
	public Set<String> getLocalisation(String ville) {
		Set<String> set = new HashSet<String>();
		int id = villeService.getVille(ville);
		List<Localisation> list = localisationService.getLocalisations(id);
		for (Localisation i : list) {
			set.add(i.getNom_Loc());
		}
		return set;
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
	public String listCamion(Model model) {
		logger.debug("IN: User/list-GET");

		List<Camion> camion = camionService.getCamion();
		model.addAttribute("camion", camion);

		// if there was an error in /add, we do not want to overwrite
		// the existing camion object containing the errors.
		if (!model.containsAttribute("camionDTO")) {
			logger.debug("Adding camionDTO object to model");
			CamionDTO camionDTO = new CamionDTO();
			model.addAttribute("camionDTO", camionDTO);
		}
		return "camion-list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@PreAuthorize("hasRole('CTRL_USER_ADD_POST')")
	public String addCamion(@Valid @ModelAttribute CamionDTO camionDTO, BindingResult result,
			RedirectAttributes redirectAttrs) {

		logger.debug("IN: Camion/add-POST");

		if (result.hasErrors()) {
			logger.debug("CamionDTO add error: " + result.toString());
			redirectAttrs.addFlashAttribute("org.springframework.validation.BindingResult.camionDTO", result);
			redirectAttrs.addFlashAttribute("camionDTO", camionDTO);
			return "redirect:/camion/list";
		} else {
			Camion camion = new Camion();
			camion = getCamion(camionDTO);
			camionService.addCamion(camion);
			return "redirect:/camion/list";
		}
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	@PreAuthorize("hasRole('CTRL_USER_EDIT_GET')")
	public String editCamionPage(@RequestParam(value = "id", required = true) Integer id, Model model,
			RedirectAttributes redirectAttrs) {

		logger.debug("IN: Camion/edit-GET:  ID to query = " + id);

		if (!model.containsAttribute("camionDTO")) {
			logger.debug("Adding camionDTO object to model");
			Camion camion = camionService.getCamion(id);
			System.out.println("/////////////////////////////////im heeeereee camion/////////////");
			CamionDTO camionDTO = getCamionDTO(camion);
			System.out.println("******************************" + camion.getNom_audite());
			logger.debug("Camion/edit-GET:  " + camionDTO.toString());
			model.addAttribute("camionDTO", camionDTO);
		}
		return "camion-edit";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@PreAuthorize("hasRole('CTRL_USER_EDIT_POST')")
	public String editCamion(@Valid @ModelAttribute CamionDTO camionDTO, BindingResult result,
			RedirectAttributes redirectAttrs, @RequestParam(value = "action", required = true) String action) {

		logger.debug("IN: Camion/edit-POST: " + action);

		if (action.equals(messageSource.getMessage("button.action.cancel", null, Locale.US))) {
			// String message =
			// messageSource.getMessage("ctrl.message.success.cancel",
			// new Object[] {"Edit", businessObject, camionDTO.getCamionname()},
			// Locale.US);
			// redirectAttrs.addFlashAttribute("message", message);
		} else if (result.hasErrors()) {
			logger.debug("Camion-edit error: " + result.toString());
			redirectAttrs.addFlashAttribute("org.springframework.validation.BindingResult.camionDTO", result);
			redirectAttrs.addFlashAttribute("camionDTO", camionDTO);
			return "redirect:/camion/edit?id=" + camionDTO.getID_AUDITE();
		} else if (action.equals(messageSource.getMessage("button.action.save", null, Locale.US))) {
			logger.debug("Camion/edit-POST:  " + camionDTO.toString());
			System.out.println("im in ediiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiit");
			Camion camion = getCamion(camionDTO);
			System.out.println("aaaaaaaaaaaaaaaaaaaaaaa" + camion.getNom_audite());
			camionService.updateCamion(camion);
			System.out.println("updaaaaaaaaaaaaaaaaaaaateeeeeeeeeeeeeeeed");
			System.out.println("aaaaaaaaaaaaaaaaaaaaaaa" + camion.getNom_audite());
		}
		return "redirect:/camion/list";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	@PreAuthorize("hasRole('CTRL_USER_DELETE_GET')")
	public String deleteCamion(@RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "phase", required = true) String phase, Model model,
			RedirectAttributes redirectAttrs) {

		Camion camion;
		camion = camionService.getCamion(id);

		logger.debug("IN: Camion/delete-GET | id = " + id + " | phase = " + phase + " | " + camion.toString());

		if (phase.equals(messageSource.getMessage("button.action.cancel", null, Locale.US))) {
			// String message =
			// messageSource.getMessage("ctrl.message.success.cancel",
			// new Object[] {"Delete", businessObject, camion.getCamionname()},
			// Locale.US);
			// redirectAttrs.addFlashAttribute("message", message);
			return "redirect:/camion/list";
		} else if (phase.equals(messageSource.getMessage("button.action.stage", null, Locale.US))) {
			logger.debug("     adding camion : " + camion.toString());
			model.addAttribute("camion", camion);
			return "camion-delete";
		} else if (phase.equals(messageSource.getMessage("button.action.delete", null, Locale.US))) {
			camionService.deleteCamion(camion.getID_AUDITE());
			// String message =
			// messageSource.getMessage("ctrl.message.success.delete",
			// new Object[] {businessObject, camion.getCamionname()},
			// Locale.US);
			// redirectAttrs.addFlashAttribute("message", message);
			return "redirect:/camion/list";
		}

		return "redirect:/camion/list";
	}

	@PreAuthorize("hasAnyRole('CTRL_USER_EDIT_GET','CTRL_USER_DELETE_GET')")
	public CamionDTO getCamionDTO(Camion camion) {
		CamionDTO camionDTO = new CamionDTO();
		camionDTO.setID_AUDITE(camion.getID_AUDITE());
		camionDTO.setNom_audite(camion.getNom_audite());
		camionDTO.setDtype(camion.getDtype());
		camionDTO.setIsValid(camion.getIsValid());
		camionDTO.setMatricule(camion.getMatricule());
		Operator operator = new Operator();
		operator = camion.getOperator();
		camionDTO.setID_OP(operator.getID_OP());
		Localisation localisation = new Localisation();
		localisation = camion.getLocalisation();
		camionDTO.setID_LOC(localisation.getID_LOC());

		return camionDTO;
	}

	@PreAuthorize("hasAnyRole('CTRL_USER_ADD_POST','CTRL_USER_EDIT_POST')")
	public Camion getCamion(CamionDTO camionDTO) {
		Camion camion = new Camion();
		camion.setID_AUDITE(camionDTO.getID_AUDITE());
		camion.setNom_audite(camionDTO.getNom_audite());
		camion.setDtype(camionDTO.getDtype());
		camion.setIsValid(camionDTO.getIsValid());
		camion.setMatricule(camionDTO.getMatricule());
		Operator operator = new Operator();
		operator = operatorService.getOperator(camionDTO.getID_OP());
		camion.setOperator(operator);
		Localisation localisation = new Localisation();
		localisation = localisationService.getLocalisation(camionDTO.getID_LOC());
		camion.setLocalisation(localisation);
		return camion;
	}

	@RequestMapping(value = { "/", "/search" }, method = RequestMethod.GET)
	@PreAuthorize("hasRole('CTRL_USER_LIST_GET')")
	public String searchUsers(@RequestParam(value = "nom_audite", required = false) String nom_audite,
			@RequestParam(value = "operator", required = false) String operator,
			@RequestParam(value = "Governorate", required = false) String gouvernorat,
			@RequestParam(value = "Ville", required = false) String ville,
			@RequestParam(value = "Localisation", required = false) String localisation, Model model,
			RedirectAttributes redirectAttrs) {
		logger.debug("IN: Camion/list-GET");

		List<Camion> camion = camionService.getCamionByName(nom_audite, operator, gouvernorat, ville, localisation);
		model.addAttribute("camion", camion);

		// if there was an error in /add, we do not want to overwrite
		// the existing user object containing the errors.
		if (!model.containsAttribute("camionDTO")) {
			logger.debug("Adding UserDTO object to model");
			CamionDTO camionDTO = new CamionDTO();
			model.addAttribute("camionDTO", camionDTO);
		}
		return "camion-list";
	}

}
