package com.palmcommerce.funds.ca.web;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.palmcommerce.funds.ca.model.Cacrts;
import com.palmcommerce.funds.ca.model.Cakeys;
import com.palmcommerce.funds.ca.util.CsrSignedManager;

import org.springframework.beans.factory.annotation.Autowired;
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

@RequestMapping("/cacrtses")
@Controller
@RooWebScaffold(path = "cacrtses", formBackingObject = Cacrts.class)
@RooWebFinder
@RooWebJson(jsonObject = Cacrts.class)
public class CacrtsController {
	
	
	@Autowired
	CsrSignedManager csrSignedManager;
	
	
	

	/**
	 * @return the csrSignedManager
	 */
	public CsrSignedManager getCsrSignedManager() {
		return csrSignedManager;
	}




	/**
	 * @param csrSignedManager the csrSignedManager to set
	 */
	public void setCsrSignedManager(CsrSignedManager csrSignedManager) {
		this.csrSignedManager = csrSignedManager;
	}




	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> createFromJson(@RequestBody String json) {
        Cacrts cacrts = Cacrts.fromJsonToCacrts(json);
       //.deleteCacrtsesByNodecodeEquals(cacrts.getNodecode());
        List<Cacrts> crts=Cacrts.findCacrtsesByNodecodeEquals(cacrts.getNodecode()).getResultList();
		
		if(cacrts!=null){
			for(Cacrts ca:crts){
				ca.remove();
			}
			//cacrts.removeAll(cacrts);
		}
		
      
        // should conver the csr to crt
        // read the file and write to the file system.
        if(cacrts.getIscsr()){
        	String nodecode=cacrts.getNodecode();
        	String csrContent;
			
				csrContent = cacrts.getContent();//new String(cacrts.getFile(),"utf-8");
				this.csrSignedManager.executeSignCsrContent(csrContent, nodecode);
			
        	
        }else{
        	cacrts.persist();
        }
       // cacrts.persist();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }
}
