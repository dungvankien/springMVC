package com.example.currencyconversion.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ConversionController {
    @GetMapping("/currency")
    public String currency(){
        return "index";
    }
    @PostMapping("/conversion")
    public String conversion(@RequestParam String usd, String rate, Model model){
        double result = Double.parseDouble(usd) * Double.parseDouble(rate);
        model.addAttribute("result",result);
        model.addAttribute("usd",usd);
        return "index";
    }
}
