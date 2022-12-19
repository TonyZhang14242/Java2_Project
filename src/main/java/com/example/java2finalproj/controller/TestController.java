package com.example.java2finalproj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @RequestMapping("/index.html")
    public String index(){
        return "index";
    }
}
