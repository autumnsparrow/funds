package com.palmcommerce.funds.mock.client.model.mock.web;

import Ice.StringHolder;

import com.palmcommerce.funds.connection.mina.ctx.MinaContext;
import com.palmcommerce.funds.mock.client.model.FromCodeEnum;
import com.palmcommerce.funds.mock.client.model.Protocol240001;
import com.palmcommerce.funds.mock.client.model.SerialNumberGenerator;
import com.palmcommerce.funds.mock.client.model.ToCodeEnum;
import com.palmcommerce.funds.mock.client.model.TranscodeEnum;
import com.palmcommerce.funds.packet.Packet;
import com.palmcommerce.funds.packet.PacketFactory;
import com.palmcommerce.funds.service.ProtocolProcessException;
import com.palmcommerce.funds.service.ProtocolStorageException;
import com.palmcommerce.funds.trade.impl.DefaultBankClientProtocolHandler;

import java.io.UnsupportedEncodingException;
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

@RequestMapping("/protocol240001s")
@Controller
@RooWebScaffold(path = "protocol240001s", formBackingObject = Protocol240001.class)
public class Protocol240001Controller {

    private static final Log logger = LogFactory.getLog(Protocol240001Controller.class);

   
    @Autowired
    DefaultBankClientProtocolHandler bankClientProtocolHandler;
    

    @RequestMapping(method = RequestMethod.POST, produces = "text/html")
    public String create(@Valid Protocol240001 protocol240001, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest) {
        if (bindingResult.hasErrors()) {
            populateEditForm(uiModel, protocol240001);
            return "protocol240001s/create";
        }
        uiModel.asMap().clear();
        Packet request = PacketFactory.getFactory().getPacket();
        request.transCode = protocol240001.getTranscode().toString().substring("Protocol".length());
        request.fromCode = protocol240001.getFromcode().toString();
        request.toCode = protocol240001.getTocode().toString();
        request.signatureLength = protocol240001.getSiglen();
        request.content = protocol240001.toString();
        StringHolder response = new StringHolder();
       
        	try {
				bankClientProtocolHandler.doProcess(request.transCode, request.fromCode, request.toCode, request.content, response);
			} catch (ProtocolProcessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	//bankClientProtocolHandler.process(request.transCode, request.fromCode, request.toCode, request.content, response);
       
        logger.info("Request:" + request.content + " \nResponse:" + response.value + " ---- " + request.toString());
      
		protocol240001.setResponse(response.value);
	
       try {
		protocol240001.persist();
       } catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
       }
        return "redirect:/protocol240001s/" + encodeUrlPathSegment(protocol240001.getId().toString(), httpServletRequest);
    }

    void populateEditForm(Model uiModel, Protocol240001 protocol240001) {
        protocol240001.setSiglen(128);
        protocol240001.setSerialNumber(SerialNumberGenerator.generate());
        protocol240001.setTransactionDatetime(SerialNumberGenerator.getTransactionDatetime());
        protocol240001.setUserId(SerialNumberGenerator.generateUserId());
        uiModel.addAttribute("protocol240001", protocol240001);
        uiModel.addAttribute("fromcodeenums", Arrays.asList(FromCodeEnum.values()));
        uiModel.addAttribute("tocodeenums", Arrays.asList(ToCodeEnum.values()));
        uiModel.addAttribute("transcodeenums", Arrays.asList(TranscodeEnum.values()));
    }
}
