package com.example.plan.controller;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
public class LogController {

  private Logger logger = Logger.getLogger(LogController.class);

  @GetMapping("/")
  public String printHello(ModelMap model) {
    logger.debug("DEBUG LEVEL");
    logger.info("INFO LEVEL");
    logger.warn("WARN LEVEL");
    logger.error("ERROR LEVEL");
    logger.fatal("FATAL LEVEL");
    return "Hello";
  }
}
