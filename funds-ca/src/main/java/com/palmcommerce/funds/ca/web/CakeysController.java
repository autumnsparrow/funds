package com.palmcommerce.funds.ca.web;

import java.util.List;

import com.palmcommerce.funds.ca.model.Cakeys;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.roo.addon.web.mvc.controller.finder.RooWebFinder;
import org.springframework.roo.addon.web.mvc.controller.json.RooWebJson;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/cakeyses")
@Controller
@RooWebScaffold(path = "cakeyses", formBackingObject = Cakeys.class)
@RooWebFinder
@RooWebJson(jsonObject = Cakeys.class)
public class CakeysController {
	
	
	 @RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
	    public ResponseEntity<String> createFromJson(@RequestBody String json) {
		 
		 	
	        Cakeys cakeys = Cakeys.fromJsonToCakeys(json);
	        List<Cakeys> keys=Cakeys.findCakeysesByNodecodeEquals(cakeys.getNodecode()).getResultList();
	        if(keys!=null){
	        	for(Cakeys k:keys){
	        		k.remove();
	        	}
	        }
	        //Cakeys.deleteCakeysesByNodecodeEquals(cakeys.getNodecode());
	        cakeys.persist();
	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Content-Type", "application/json");
	        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	    }
}
