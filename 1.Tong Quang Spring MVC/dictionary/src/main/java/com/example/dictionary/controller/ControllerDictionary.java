package com.example.dictionary.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;
@Controller
public class ControllerDictionary {
    @GetMapping("/dictionary")
    public String getIndex(){
        return "index";
    }
    @PostMapping("/search")
    public String postIndex(@RequestParam String english, Model model){
        String result = resultVocabulary(english);
        model.addAttribute("result", result);
        model.addAttribute("english", english);
        return "index";
    }
    public String resultVocabulary(String vocabulary){
        Map<String, String> listVocabulary = new HashMap<>();
        listVocabulary.put("hello", "Xin chào");
        listVocabulary.put("no", "Không");
        listVocabulary.put("go", "đi");
        listVocabulary.put("search", "Tìm kiếm");
           if(listVocabulary.get(vocabulary)!= null){
            return listVocabulary.get(vocabulary);
           } else {
               return "Library doesn't have this vocabulary";
           }
    }
}
