package com.globe.gest.controller;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping(value = "/map")
@PreAuthorize("denyAll")
public class MapController {

	static Logger logger = LoggerFactory.getLogger(MapController.class);
	static String businessObject = "map"; // used in RedirectAttributes
												// messages

	


	@RequestMapping(value = { "/","/map" }, method = RequestMethod.GET)
	@PreAuthorize("hasRole('CTRL_USER_LIST_GET')")
	public String listMap(Model model) {
		logger.debug("IN: User/list-GET");

		
		return "map";
	}

	

}
