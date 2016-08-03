package com.globe.gest.controller;

import java.util.List;
import java.util.Locale;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.globe.gest.model.Camion;

import com.globe.gest.service.CamionService;

@Controller
@RequestMapping(value="/camion")
@PreAuthorize("denyAll")
public class CamionController {
	
	
	static Logger logger = LoggerFactory.getLogger(CamionController.class);
    static String businessObject = "camion"; //used in RedirectAttributes messages 
    
    @Autowired
    private CamionService camionService;
    
    @Autowired
    private MessageSource messageSource;
    
    @RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)
    @PreAuthorize("hasRole('CTRL_USER_LIST_GET')")
    public String listShops(Model model) {
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
    public String addCamion(@Valid @ModelAttribute CamionDTO camionDTO,
            BindingResult result, RedirectAttributes redirectAttrs) {
        
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
    public String editCamionPage(@RequestParam(value = "id", required = true)
            Integer id, Model model, RedirectAttributes redirectAttrs) {

        logger.debug("IN: Camion/edit-GET:  ID to query = " + id);

        if (!model.containsAttribute("camionDTO")) {
		    logger.debug("Adding camionDTO object to model");
		    Camion camion = camionService.getCamion(id);
		    CamionDTO camionDTO = getCamionDTO(camion);
		    logger.debug("Camion/edit-GET:  " + camionDTO.toString());
		    model.addAttribute("camionDTO", camionDTO);
		}
		return "camion-edit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @PreAuthorize("hasRole('CTRL_USER_EDIT_POST')")
    public String editCamion(@Valid @ModelAttribute CamionDTO camionDTO,
            BindingResult result, RedirectAttributes redirectAttrs,
            @RequestParam(value = "action", required = true) String action) {

        logger.debug("IN: Camion/edit-POST: " + action);

        if (action.equals(messageSource.getMessage("button.action.cancel", null, Locale.US))) {
           // String message = messageSource.getMessage("ctrl.message.success.cancel", 
             //       new Object[] {"Edit", businessObject, camionDTO.getCamionname()}, Locale.US);
            //redirectAttrs.addFlashAttribute("message", message);
        } else if (result.hasErrors()) {
            logger.debug("Camion-edit error: " + result.toString());
            redirectAttrs.addFlashAttribute("org.springframework.validation.BindingResult.camionDTO", result);
            redirectAttrs.addFlashAttribute("camionDTO", camionDTO);
            return "redirect:/camion/edit?id=" + camionDTO.getID_AUDITE();
        } else if (action.equals(messageSource.getMessage("button.action.save",  null, Locale.US))) {
            logger.debug("Camion/edit-POST:  " + camionDTO.toString());
            Camion camion = getCamion(camionDTO);
			camionService.updateCamion(camion);
        }
        return "redirect:/camion/list";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    @PreAuthorize("hasRole('CTRL_USER_DELETE_GET')")
    public String deleteCamion(
            @RequestParam(value = "id", required = true) Integer id,
            @RequestParam(value = "phase", required = true) String phase,
            Model model, RedirectAttributes redirectAttrs) {

        Camion camion;
        camion = camionService.getCamion(id);

        logger.debug("IN: Camion/delete-GET | id = " + id + " | phase = " + phase + " | " + camion.toString());

        if (phase.equals(messageSource.getMessage("button.action.cancel", null, Locale.US))) {
            //String message = messageSource.getMessage("ctrl.message.success.cancel", 
              //      new Object[] {"Delete", businessObject, camion.getCamionname()}, Locale.US);
            //redirectAttrs.addFlashAttribute("message", message);
            return "redirect:/camion/list";
        } else if (phase.equals(messageSource.getMessage("button.action.stage", null, Locale.US))) {
            logger.debug("     adding camion : " + camion.toString());
            model.addAttribute("camion", camion);
            return "camion-delete";
        } else if (phase.equals(messageSource.getMessage("button.action.delete", null, Locale.US))) {
            camionService.deleteCamion(camion.getID_AUDITE());
           //  String message = messageSource.getMessage("ctrl.message.success.delete", 
			//        new Object[] {businessObject, camion.getCamionname()}, Locale.US);
			//redirectAttrs.addFlashAttribute("message", message);
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
        return camion;
    }
    
   
    

}