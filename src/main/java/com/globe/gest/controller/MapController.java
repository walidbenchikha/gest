package com.globe.gest.controller;



import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.globe.gest.model.Operator;
import com.globe.gest.model.Shops;
import com.globe.gest.model.User;
import com.globe.gest.service.OperatorService;
import com.globe.gest.service.ShopsService;
import com.globe.gest.service.UserService;


@Controller
@RequestMapping(value = "/map")
@PreAuthorize("denyAll")
public class MapController {

	static Logger logger = LoggerFactory.getLogger(MapController.class);
	static String businessObject = "map"; // used in RedirectAttributes
												// messages

	@Autowired
	private OperatorService operatorService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ShopsService shopsService;
	
	@ModelAttribute("allOperators")
	@PreAuthorize("hasAnyRole('CTRL_AUDITY_LIST_GET')")
	public List<Operator> getAllOperators() {
		
		return operatorService.getOperators();
	}
	
	@ModelAttribute("allAuditors")
	@PreAuthorize("hasAnyRole('CTRL_AUDITY_LIST_GET')")
	public List<User> getAllAuditors() {
		
		return userService.getAuditors();
	}
	
	
	
	@RequestMapping(value = "/loadShops", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Shops> loadShops(
//			@RequestParam("user") String user,
			@RequestParam("operator") String operator
		//	@RequestParam("governorate") String governorate
			) {

		System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOKay");
		
		//XStream xstream = new XStream();
		List<Shops> shopsMarkers = this.findShops(operator);
		//String xml = xstream.toXML(shopsMarkers);
		
		return shopsMarkers;

	}

	private List<Shops> findShops( String operator) {
		List<Shops> results = new ArrayList<Shops>();

//		ShopService shopService = ApplicationContextProvider
//				.getApplicationContext().getBean("shopService",
//						ShopService.class);

		results = shopsService.findShopsMarkers(operator);
		System.out.println("****le nombre de markeurs est = "	+ results.size());
		return results;
	}
	


	@RequestMapping(value = { "/","/map" }, method = RequestMethod.GET)
	@PreAuthorize("hasRole('CTRL_MAP_LIST_GET')")
	public String listMap(Model model) {
		logger.debug("IN: User/list-GET");

		
		return "map";
	}

	

}
