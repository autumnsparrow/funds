// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package com.palmcommerce.funds.mock.client.model.mock.web;

import com.palmcommerce.funds.mock.client.model.Protocol240002;
import com.palmcommerce.funds.mock.client.model.mock.web.Protocol240002Controller;
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

privileged aspect Protocol240002Controller_Roo_Controller {
    
    @RequestMapping(params = "form", produces = "text/html")
    public String Protocol240002Controller.createForm(Model uiModel) {
        populateEditForm(uiModel, new Protocol240002());
        return "protocol240002s/create";
    }
    
    @RequestMapping(value = "/{id}", produces = "text/html")
    public String Protocol240002Controller.show(@PathVariable("id") Long id, Model uiModel) {
        uiModel.addAttribute("protocol240002", Protocol240002.findProtocol240002(id));
        uiModel.addAttribute("itemId", id);
        return "protocol240002s/show";
    }
    
    @RequestMapping(produces = "text/html")
    public String Protocol240002Controller.list(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        if (page != null || size != null) {
            int sizeNo = size == null ? 10 : size.intValue();
            final int firstResult = page == null ? 0 : (page.intValue() - 1) * sizeNo;
            uiModel.addAttribute("protocol240002s", Protocol240002.findProtocol240002Entries(firstResult, sizeNo));
            float nrOfPages = (float) Protocol240002.countProtocol240002s() / sizeNo;
            uiModel.addAttribute("maxPages", (int) ((nrOfPages > (int) nrOfPages || nrOfPages == 0.0) ? nrOfPages + 1 : nrOfPages));
        } else {
            uiModel.addAttribute("protocol240002s", Protocol240002.findAllProtocol240002s());
        }
        return "protocol240002s/list";
    }
    
    @RequestMapping(method = RequestMethod.PUT, produces = "text/html")
    public String Protocol240002Controller.update(@Valid Protocol240002 protocol240002, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, protocol240002);
            return "protocol240002s/update";
        }
        uiModel.asMap().clear();
        protocol240002.merge();
        return "redirect:/protocol240002s/" + encodeUrlPathSegment(protocol240002.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping(value = "/{id}", params = "form", produces = "text/html")
    public String Protocol240002Controller.updateForm(@PathVariable("id") Long id, Model uiModel) {
        populateEditForm(uiModel, Protocol240002.findProtocol240002(id));
        return "protocol240002s/update";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "text/html")
    public String Protocol240002Controller.delete(@PathVariable("id") Long id, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "size", required = false) Integer size, Model uiModel) {
        Protocol240002 protocol240002 = Protocol240002.findProtocol240002(id);
        protocol240002.remove();
        uiModel.asMap().clear();
        uiModel.addAttribute("page", (page == null) ? "1" : page.toString());
        uiModel.addAttribute("size", (size == null) ? "10" : size.toString());
        return "redirect:/protocol240002s";
    }
    
    String Protocol240002Controller.encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
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