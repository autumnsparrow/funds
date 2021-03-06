// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.funds.mock.client.model.mock.web;

import com.palmcommerce.funds.mock.client.model.Protocol240004;
import com.palmcommerce.funds.mock.client.model.mock.web.Protocol240004Controller;
import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

privileged aspect Protocol240004Controller_Roo_Controller {
    
    @RequestMapping(params = "form", produces = "text/html")
    public String Protocol240004Controller.createForm(Model uiModel) {
        populateEditForm(uiModel, new Protocol240004());
        return "protocol240004s/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String Protocol240004Controller.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("protocol240004", Protocol240004.findProtocol240004(id));
        uiModel.addAttribute("itemId", id);
        return "protocol240004s/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String Protocol240004Controller.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("protocol240004s", Protocol240004.findProtocol240004Entries(firstResult, sizeNo));
            float nrOfPages = (float) Protocol240004.countProtocol240004s() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("protocol240004s", Protocol240004.findAllProtocol240004s());
        }
        return "protocol240004s/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String Protocol240004Controller.update(@Valid Protocol240004 protocol240004, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, protocol240004);
            return "protocol240004s/update";
        }
        uiModel.asMap().clear();
        protocol240004.merge();
        return "redirect:/protocol240004s/" + encodeUrlPathSegment(protocol240004.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String Protocol240004Controller.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, Protocol240004.findProtocol240004(id));
        return "protocol240004s/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String Protocol240004Controller.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Protocol240004 protocol240004 = Protocol240004.findProtocol240004(id);
        protocol240004.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/protocol240004s";
    }
    
    String Protocol240004Controller.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String enc = httpServletRequest.getCharacterEncoding();
        if (enc == null) {
            enc = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        try {
            pathSegment = UriUtils.encodePathSegment(pathSegment, enc);
        } catch (UnsupportedEncodingException uee) {}
        return pathSegment;
    }
    
}
