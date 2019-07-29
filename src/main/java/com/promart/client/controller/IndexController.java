package com.promart.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class IndexController {

	/**Method to show the main view*/
  @RequestMapping(value="/", method= RequestMethod.GET)
  public String index() {
    return "index";
  }

}
