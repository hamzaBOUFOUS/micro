package com.hb.security.controller;

import com.hb.security.services.ProduitService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {

    private ProduitService helloService;

    public HelloController(ProduitService helloService){
        this.helloService = helloService;
    }

    @GetMapping("/test")
    public String testHello(){
        return "this.helloService.getValueFromFile()";
    }
}
