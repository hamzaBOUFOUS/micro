package com.hb.security.dto.mapper;


import com.hb.security.dto.ProduitDTO;
import com.hb.security.entity.Produit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProduitMapper {

    Produit toProduit(ProduitDTO produitDTO);
    ProduitDTO toProduitDto(Produit produit);
}
