package com.globe.gest.controller;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

import com.globe.gest.model.Audite;
import com.globe.gest.model.Operator;
import com.globe.gest.model.STAT1;
import com.globe.gest.model.Track;
import com.globe.gest.model.User;
import com.globe.gest.service.OperatorService;
import com.globe.gest.service.ShopsService;
import com.globe.gest.service.TrackService;
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
	private TrackService trackService;
	
	@Autowired
	private ShopsService shopsService;
	
	@ModelAttribute("allOperators")
	@PreAuthorize("hasAnyRole('CTRL_MAP_LIST_GET')")
	public List<Operator> getAllOperators() {
		
		return operatorService.getOperators();
	}
	
	@ModelAttribute("allAuditors")
	@PreAuthorize("hasAnyRole('CTRL_MAP_LIST_GET')")
	public List<User> getAllAuditors() {
		
		return userService.getAuditors();
	}
	
	@ModelAttribute("allAuditors1")
	@PreAuthorize("hasRole('CTRL_MAP_LIST_GET')")
	@ResponseBody
	public Map<Integer,String> getAuditors() {
		Map<Integer,String> m1 = new HashMap<>(); 
		List<User>  list= userService.getAuditors();
		for(User i:list){
			m1.put(i.getId(),i.getUsername());
		}
		return  m1;

	}
	
//	
//	@RequestMapping(value = "/loadShops")
//	@PreAuthorize("hasRole('CTRL_MAP_LIST_GET')")
//	@ResponseBody
//	public List<Shops> loadShops(@RequestParam("operator") String operator, 
//			@RequestParam("auditor") String auditor) {
//
//		System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOKay");
//		
//		//XStream xstream = new XStream();
//		List<Shops> shopsMarkers = shopsService.findShopsMarkers(operator,auditor);
//		Iterator<Shops> iterator = shopsMarkers.iterator();
//		while (iterator.hasNext()) {
//			System.out.println(iterator.next().getNom_audite());
//		}
//		System.out.println("blue");
//		//String xml = xstream.toXML(shopsMarkers);
//		//System.out.println(xml);
//		
//		return shopsMarkers;
//
//	}

	
	@RequestMapping(value = "/loadShops")
	@PreAuthorize("hasRole('CTRL_MAP_LIST_GET')")
	@ResponseBody
	public List<Audite> loadShops(@RequestParam("operator") String operator, 
			@RequestParam("auditor") String auditor) {

		System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOKay");
		
		//XStream xstream = new XStream();
		List<Audite> shopsMarkers = shopsService.findShopsMarkers(operator,auditor);
		Iterator<Audite> iterator = shopsMarkers.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next().getNom_audite());
		}
		System.out.println("blue");
		//String xml = xstream.toXML(shopsMarkers);
		//System.out.println(xml);
		
		return shopsMarkers;

	}
	


	@RequestMapping(value = { "/","/map" }, method = RequestMethod.GET)
	@PreAuthorize("hasRole('CTRL_MAP_LIST_GET')")
	public String listMap(Model model) {
		logger.debug("IN: User/list-GET");

		
		return "map";
	}
	
	
	@RequestMapping(value = "/loadTrack", method = RequestMethod.GET, produces = "application/json")
	@PreAuthorize("hasRole('CTRL_MAP_LIST_GET')")
	public @ResponseBody List loadTrack(
			@RequestParam("auditor") int auditor) {

		//XStream xstream = new XStream();
		List<Track> tracking = this.findTrack(auditor);
		//String xml = xstream.toXML(shopsMarkers);

		return tracking;

	}

	public List findTrack(int auditor) {
		List results = new ArrayList();

//		ShopService shopService = ApplicationContextProvider
//				.getApplicationContext().getBean("shopService",
//						ShopService.class);

		results = trackService.findUserTrack(auditor);
		System.out.println("****le nombre de point tracking est = "	+ results.size());
		return results;
	}

	
	@RequestMapping(value = "/loadSTAT1", method = RequestMethod.GET, produces = "application/json")
	@PreAuthorize("hasRole('CTRL_MAP_LIST_GET')")
	public @ResponseBody List<STAT1> findSTAT1( @RequestParam("operator") String operator, 
			@RequestParam("auditor") String auditor ) {
//		List<STAT1> results = new ArrayList<STAT1>();
//		ShopService shopService = ApplicationContextProvider
//				.getApplicationContext().getBean("shopService",
//						ShopService.class);
		System.out.println("****STAT1 = "	+ auditor + "et "+ operator + "kkkkkkkkkkkk");
		List<STAT1> results = shopsService.findSTAT1( operator,  auditor );
		System.out.println("****STAT1 = "	+ results.size());
		return results;
	}
	

}
