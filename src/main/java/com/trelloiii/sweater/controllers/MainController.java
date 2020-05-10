package com.trelloiii.sweater.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {

    @GetMapping("/name")
    public String index(@RequestParam(required = false,defaultValue = "World") String name,
                        Map<String,Object> model){
        model.put("name",name);
        return "index";
    }
}
