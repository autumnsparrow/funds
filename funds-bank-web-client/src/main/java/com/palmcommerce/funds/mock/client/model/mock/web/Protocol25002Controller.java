package com.palmcommerce.funds.mock.client.model.mock.web;

import com.palmcommerce.funds.mock.client.model.Protocol25002;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/protocol25002s")
@Controller
@RooWebScaffold(path = "protocol25002s", formBackingObject = Protocol25002.class)
public class Protocol25002Controller {
}
