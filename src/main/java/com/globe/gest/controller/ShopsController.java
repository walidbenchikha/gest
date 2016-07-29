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
import com.globe.gest.service.AuditeService;

@Controller
@RequestMapping(value="/shops")
@PreAuthorize("denyAll")
public class ShopsController {
	
	
	static Logger logger = LoggerFactory.getLogger(ShopsController.class);
    static String businessObject = "audite"; //used in RedirectAttributes messages 
    
    @Autowired
    private AuditeService auditeService;
    
    @RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)
    @PreAuthorize("hasRole('CTRL_USER_LIST_GET')")
    public String listShops(Model model) {
        logger.debug("IN: User/list-GET");

        List<Audite> audite = auditeService.getAudite();
        model.addAttribute("audite", audite);

        // if there was an error in /add, we do not want to overwrite
        // the existing user object containing the errors.
//        if (!model.containsAttribute("auditeDTO")) {
//            logger.debug("Adding shopsDTO object to model");
//            AuditeDTO auditeDTO = new AuditeDTO();
//            model.addAttribute("auditeDTO", auditeDTO);
//        }
        return "audite-list";
    }
    
    

}
