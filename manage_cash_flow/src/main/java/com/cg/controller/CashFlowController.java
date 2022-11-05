package com.cg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class CashFlowController {
   @GetMapping
    public ModelAndView showCashFlow(){
       ModelAndView modelAndView = new ModelAndView("/cashFlow/list");
       return modelAndView;
    }
}
