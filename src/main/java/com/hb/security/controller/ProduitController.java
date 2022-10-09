package com.hb.security.controller;

import com.hb.security.entity.Orders;
import com.hb.security.entity.Produit;
import com.hb.security.services.ProduitService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/produit")
public class ProduitController {

    private ProduitService produitService;

    public ProduitController(ProduitService produitService){
        this.produitService = produitService;
    }

    @GetMapping("/all")
    public List<Produit> testHello(){
        DateTimeFormatter monthFormatter
                = DateTimeFormatter.ofPattern("MM-dd");
        System.out.println(LocalDate.now().format(monthFormatter));
        return this.produitService.listAllProduit();
    }
}
