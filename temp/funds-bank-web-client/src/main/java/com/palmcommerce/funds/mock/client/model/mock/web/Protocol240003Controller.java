package com.palmcommerce.funds.mock.client.model.mock.web;

import Ice.StringHolder;

import com.palmcommerce.funds.mock.client.model.FromCodeEnum;
import com.palmcommerce.funds.mock.client.model.Protocol240003;
import com.palmcommerce.funds.mock.client.model.ToCodeEnum;
import com.palmcommerce.funds.mock.client.model.TranscodeEnum;
import com.palmcommerce.funds.packet.Packet;
import com.palmcommerce.funds.packet.PacketFactory;
import com.palmcommerce.funds.service.ProtocolProcessException;
import com.palmcommerce.funds.service.ProtocolStorageException;
import com.palmcommerce.funds.trade.impl.DefaultBankClientProtocolHandler;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/protocol240003s")
@Controller
@RooWebScaffold(path = "protocol240003s", formBackingObject = Protocol240003.class)
public class Protocol240003Controller {

    private static final Log logger = LogFactory.getLog(Protocol240003Controller.class);

    @Autowired
    DefaultBankClientProtocolHandler bankClientProtocolHandler;

    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Protocol240003 protocol240003, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, protocol240003);
            return "protocol240003s/create";
        }
        uiModel.asMap().clear();
        Packet request = PacketFactory.getFactory().getPacket();
        request.transCode = protocol240003.getTranscode().toString().substring("Protocol".length());
        request.fromCode = protocol240003.getFromcode().toString();
        request.toCode = protocol240003.getTocode().toString();
        request.signatureLength = protocol240003.getSiglen();
        request.content = protocol240003.toString();
        StringHolder response = new StringHolder();
       
        try {
			bankClientProtocolHandler.doProcess(request.transCode, request.fromCode, request.toCode, request.content, response);
		} catch (ProtocolProcessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
        logger.info("Request:" + request.content + " \nResponse:" + response.value + " ---- " + request.toString());
       protocol240003.setResponse(response.value);
        protocol240003.persist();
        return "redirect:/protocol240003s/" + encodeUrlPathSegment(protocol240003.getId().toString(), httpServletRequest);
    }

    void populateEditForm(Model uiModel, Protocol240003 protocol240003) {
        protocol240003.initialize();
        uiModel.addAttribute("protocol240003", protocol240003);
        uiModel.addAttribute("fromcodeenums", Arrays.asList(FromCodeEnum.values()));
        uiModel.addAttribute("tocodeenums", Arrays.asList(ToCodeEnum.values()));
        uiModel.addAttribute("transcodeenums", Arrays.asList(TranscodeEnum.values()));
    }
}
