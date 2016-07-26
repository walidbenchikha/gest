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

import com.globe.gest.model.Shops;
import com.globe.gest.service.ShopsService;

@Controller
@RequestMapping(value="/shops")
@PreAuthorize("denyAll")
public class ShopsController {
	
	
	static Logger logger = LoggerFactory.getLogger(ShopsController.class);
    static String businessObject = "shops"; //used in RedirectAttributes messages 
    
    @Autowired
    private ShopsService shopsService;
    
    @RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)
    @PreAuthorize("hasRole('CTRL_USER_LIST_GET')")
    public String listShops(Model model) {
        logger.debug("IN: User/list-GET");

        List<Shops> shops = shopsService.getShops();
        model.addAttribute("shops", shops);

        // if there was an error in /add, we do not want to overwrite
        // the existing user object containing the errors.
        if (!model.containsAttribute("shopsDTO")) {
            logger.debug("Adding shopsDTO object to model");
            ShopsDTO shopsDTO = new ShopsDTO();
            model.addAttribute("shopsDTO", shopsDTO);
        }
        return "shops-list";
    }
    
    

}
