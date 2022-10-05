package com.hb.security.services;

import com.hb.security.entity.Produit;
import com.hb.security.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduitService {

    private ProduitRepository produitRepository;

    @Autowired
    public ProduitService(ProduitRepository produitRepository){
        this.produitRepository = produitRepository;
    }

}
