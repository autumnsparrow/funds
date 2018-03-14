package com.palmcommerce.funds.mock.client.model.mock.web;

import com.palmcommerce.funds.mock.client.model.ProtocolP25002;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/protocolp25002s")
@Controller
@RooWebScaffold(path = "protocolp25002s", formBackingObject = ProtocolP25002.class)
public class ProtocolP25002Controller {
}
