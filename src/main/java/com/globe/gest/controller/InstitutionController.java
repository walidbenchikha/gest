package com.globe.gest.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
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

import com.globe.gest.model.Gouvernorat;
import com.globe.gest.model.Institution;
import com.globe.gest.model.Localisation;
import com.globe.gest.model.Operator;
import com.globe.gest.model.Ville;
import com.globe.gest.service.GouvernoratService;
import com.globe.gest.service.InstitutionService;
import com.globe.gest.service.LocalisationService;
import com.globe.gest.service.OperatorService;
import com.globe.gest.service.VilleService;
 
 @Controller
 @RequestMapping(value = "/institution")
 @PreAuthorize("denyAll")
 public class InstitutionController {
	
 	static Logger logger = LoggerFactory.getLogger(InstitutionController.class);
    static String businessObject = "institution"; //used in RedirectAttributes messages 
    
    @Autowired
    private InstitutionService institutionService;
    
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
	@PreAuthorize("hasRole('CTRL_AUDITY_LIST_GET')")
	@ResponseBody
	public Map<Integer,String> getVilles(int gouvernorat) {
		Map<Integer,String> m1 = new HashMap<>(); 
		List<Ville>  list= villeService.getVille(gouvernorat);
		for(Ville i:list){
			m1.put(i.getID_ville(),i.getNom_Ville());
		}
		return  m1;

	}
	


	
	@RequestMapping(value = "/localisation")
	@PreAuthorize("hasRole('CTRL_AUDITY_LIST_GET')")
	@ResponseBody
	public Map<Integer,String> getLocalisations(int ville) {
		Map<Integer,String> m1 = new HashMap<>(); 
		List<Localisation>  list= localisationService.getLocalisations(ville);
		for(Localisation i:list){
			m1.put(i.getID_LOC(),i.getNom_Loc());
		}
		return  m1;

	}
	
	
	@RequestMapping(value = "/getVille")
	@PreAuthorize("hasRole('CTRL_AUDITY_LIST_GET')")
	@ResponseBody
	public int getVille(int localisation) {
		int ville= localisationService.getVille(localisation);
		return ville;
	}
	
	@RequestMapping(value = "/getGouvernorat")
	@PreAuthorize("hasRole('CTRL_AUDITY_LIST_GET')")
	@ResponseBody
	public int getGouvernorat(int ville) {
		return villeService.getGouvernorat(ville);
				
	
	}
	
	@ModelAttribute("allOperators")
	@PreAuthorize("hasAnyRole('CTRL_AUDITY_LIST_GET')")
	public List<Operator> getAllOperators() {
		
		return operatorService.getOperators();
	}
	
	@ModelAttribute("allGouvernorat")
	@PreAuthorize("hasAnyRole('CTRL_AUDITY_LIST_GET')")
	public List<Gouvernorat> getAllGouvernorat() {
		return gouvernoratService.getGouvernorat();
	}
	

	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	@PreAuthorize("hasRole('CTRL_AUDITY_LIST_GET')")
	public String listShops(Model model) {
		logger.debug("IN: Institution/list-GET");

		List<Institution> institution = institutionService.getInstitution();
		model.addAttribute("institution", institution);

		// if there was an error in /add, we do not want to overwrite
		// the existing user object containing the errors.
		if (!model.containsAttribute("institutionDTO")) {
			logger.debug("Adding institutionDTO object to model");
			InstitutionDTO institutionDTO = new InstitutionDTO();
			model.addAttribute("institutionDTO", institutionDTO);
		}
		return "institution-list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@PreAuthorize("hasRole('CTRL_AUDITY_ADD_POST')")
	public String addInstitution(@Valid @ModelAttribute InstitutionDTO institutionDTO, BindingResult result,
			RedirectAttributes redirectAttrs) {

		logger.debug("IN: Institution/add-POST");

		if (result.hasErrors()) {
			logger.debug("InstitutionDTO add error: " + result.toString());
			redirectAttrs.addFlashAttribute("org.springframework.validation.BindingResult.institutionDTO", result);
			redirectAttrs.addFlashAttribute("institutionDTO", institutionDTO);
			return "redirect:/institution/list";
		} else {
			Institution institution = new Institution();
			institution = getInstitution(institutionDTO);
			institutionService.addInstitution(institution);
			return "redirect:/institution/list";
		}
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	@PreAuthorize("hasRole('CTRL_AUDITY_EDIT_GET')")
	public String editInstitutionPage(@RequestParam(value = "id", required = true) Integer id, Model model,
			RedirectAttributes redirectAttrs) {

		logger.debug("IN: Institution/edit-GET:  ID to query = " + id);

		if (!model.containsAttribute("institutionDTO")) {
			logger.debug("Adding institutionDTO object to model");
			Institution institution = institutionService.getInstitution(id);
			InstitutionDTO institutionDTO = getInstitutionDTO(institution);
			logger.debug("Institution/edit-GET:  " + institutionDTO.toString());
			model.addAttribute("institutionDTO", institutionDTO);
		}
		return "institution-edit";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@PreAuthorize("hasRole('CTRL_AUDITY_EDIT_POST')")
	public String editInstitution(@Valid @ModelAttribute InstitutionDTO institutionDTO, BindingResult result,
			RedirectAttributes redirectAttrs, @RequestParam(value = "action", required = true) String action) {

		logger.debug("IN: Institution/edit-POST: " + action);

		if (action.equals(messageSource.getMessage("button.action.cancel", null, Locale.US))) {
			// String message =
			// messageSource.getMessage("ctrl.message.success.cancel",
			// new Object[] {"Edit", businessObject,
			// institutionDTO.getInstitutionname()}, Locale.US);
			// redirectAttrs.addFlashAttribute("message", message);
		} else if (result.hasErrors()) {
			
			logger.debug("Institution-edit error: " + result.toString());
			redirectAttrs.addFlashAttribute("org.springframework.validation.BindingResult.institutionDTO", result);
			redirectAttrs.addFlashAttribute("institutionDTO", institutionDTO);
			return "redirect:/institution/edit?id=" + institutionDTO.getID_AUDITE();
		} else if (action.equals(messageSource.getMessage("button.action.save", null, Locale.US))) {
			
			logger.debug("Institution/edit-POST:  " + institutionDTO.toString());
			Institution institution = getInstitution(institutionDTO);
			institutionService.updateInstitution(institution);
		}
		return "redirect:/institution/list";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	@PreAuthorize("hasRole('CTRL_AUDITY_DELETE_GET')")
	public String deleteInstitution(@RequestParam(value = "id", required = true) Integer id,
			@RequestParam(value = "phase", required = true) String phase, Model model,
			RedirectAttributes redirectAttrs) {
		Institution institution;
		institution = institutionService.getInstitution(id);

		logger.debug(
				"IN: Institution/delete-GET | id = " + id + " | phase = " + phase + " | " + institution.toString());

		if (phase.equals(messageSource.getMessage("button.action.cancel", null, Locale.US))) {
			// String message =
			// messageSource.getMessage("ctrl.message.success.cancel",
			// new Object[] {"Delete", businessObject,
			// institution.getInstitutionname()}, Locale.US);
			// redirectAttrs.addFlashAttribute("message", message);
			return "redirect:/institution/list";
		} else if (phase.equals(messageSource.getMessage("button.action.stage", null, Locale.US))) {
			logger.debug("     adding institution : " + institution.toString());
			model.addAttribute("institution", institution);
			return "institution-delete";
		} else if (phase.equals(messageSource.getMessage("button.action.delete", null, Locale.US))) {
			institutionService.deleteInstitution(institution.getID_AUDITE());
			// String message =
			// messageSource.getMessage("ctrl.message.success.delete",
			// new Object[] {businessObject, institution.getInstitutionname()},
			// Locale.US);
			// redirectAttrs.addFlashAttribute("message", message);
			return "redirect:/institution/list";
		}

		return "redirect:/institution/list";
}

	@PreAuthorize("hasAnyRole('CTRL_AUDITY_EDIT_GET','CTRL_AUDITY_DELETE_GET')")
	public InstitutionDTO getInstitutionDTO(Institution institution) {
		InstitutionDTO institutionDTO = new InstitutionDTO();
		institutionDTO.setID_AUDITE(institution.getID_AUDITE());
		institutionDTO.setNom_audite(institution.getNom_audite());
	    institutionDTO.setIsValid(institution.getIsValid());
		institutionDTO.setLatitude_boutique(institution.getLatitude_boutique());
		institutionDTO.setLongitude_boutique(institution.getLongitude_boutique());
		institutionDTO.setAdresse_boutique(institution.getAdresse_boutique());
		institutionDTO.setPhone_boutique(institution.getPhone_boutique());
		institutionDTO.setRaison_sociale(institution.getRaison_sociale());
		Operator operator = new Operator();
		operator = institution.getOperator();
		institutionDTO.setID_OP(operator.getID_OP());
		Localisation localisation = new Localisation();
		localisation = institution.getLocalisation();
		institutionDTO.setID_LOC(localisation.getID_LOC());
		return institutionDTO;
	}

	@PreAuthorize("hasAnyRole('CTRL_AUDITY_ADD_POST','CTRL_AUDITY_EDIT_POST')")
	public Institution getInstitution(InstitutionDTO institutionDTO) {
		Institution institution = new Institution();
		institution.setID_AUDITE(institutionDTO.getID_AUDITE());
		institution.setNom_audite(institutionDTO.getNom_audite());
		institution.setIsValid(institutionDTO.getIsValid());
		institution.setLatitude_boutique(institutionDTO.getLatitude_boutique());
		institution.setLongitude_boutique(institutionDTO.getLongitude_boutique());
		institution.setAdresse_boutique(institutionDTO.getAdresse_boutique());
		institution.setPhone_boutique(institutionDTO.getPhone_boutique());
		institution.setRaison_sociale(institutionDTO.getRaison_sociale());
		Operator operator = new Operator();
		operator = operatorService.getOperator(institutionDTO.getID_OP());
		institution.setOperator(operator);
		Localisation localisation = new Localisation();
		localisation = localisationService.getLocalisation(institutionDTO.getID_LOC());
		institution.setLocalisation(localisation);
		return institution;
	}
	
	@RequestMapping(value = {"/", "/search"}, method = RequestMethod.GET)
    @PreAuthorize("hasRole('CTRL_AUDITY_LIST_GET')")
    public String searchUsers(@RequestParam(value = "nom_audite", required = false)
    String nom_audite,
    
    @RequestParam(value = "operator", required = false) String operator,
    @RequestParam(value = "Governorate", required = false) int gouvernorat,
    @RequestParam(value = "Ville", required = false) int ville,
    @RequestParam(value = "Localisation", required = false) int localisation,
    Model model, RedirectAttributes redirectAttrs) {
        logger.debug("IN: institution/list-GET");

        List<Institution> institution = institutionService.getInstitutionByName(nom_audite,operator,gouvernorat,ville,localisation);
        model.addAttribute("institution", institution);

        // if there was an error in /add, we do not want to overwrite
        // the existing user object containing the errors.
        if (!model.containsAttribute("institutionDTO")) {
            logger.debug("Adding UserDTO object to model");
            InstitutionDTO institutionDTO = new InstitutionDTO();
            model.addAttribute("institutionDTO", institutionDTO);
        }
        return "institution-list";
    }
	
	
	
	
 
 }
