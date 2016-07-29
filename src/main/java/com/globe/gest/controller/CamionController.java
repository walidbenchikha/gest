package com.globe.gest.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.globe.gest.model.Audite;
import com.globe.gest.model.Camion;
import com.globe.gest.service.AuditeService;
import com.globe.gest.service.CamionService;

@Controller
@RequestMapping(value="/camion")
@PreAuthorize("denyAll")
public class CamionController {
	
	
	static Logger logger = LoggerFactory.getLogger(CamionController.class);
    static String businessObject = "camion"; //used in RedirectAttributes messages 
    
    @Autowired
    private CamionService camionService;
    
    @RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)
    @PreAuthorize("hasRole('CTRL_USER_LIST_GET')")
    public String listShops(Model model) {
        logger.debug("IN: User/list-GET");

        List<Camion> camion = camionService.getCamion();
        model.addAttribute("camion", camion);

        // if there was an error in /add, we do not want to overwrite
        // the existing user object containing the errors.
//        if (!model.containsAttribute("auditeDTO")) {
//            logger.debug("Adding shopsDTO object to model");
//            AuditeDTO auditeDTO = new AuditeDTO();
//            model.addAttribute("auditeDTO", auditeDTO);
//        }
        return "camion-list";
    }
    
    

}
