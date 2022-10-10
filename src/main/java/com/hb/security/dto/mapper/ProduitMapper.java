package com.hb.security.dto.mapper;


import com.hb.security.dto.ProduitDTO;
import com.hb.security.entity.Produit;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProduitMapper {

    @Mapping(target = "dateCreatedProdut", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "id", ignore = true)
    Produit toProduit(ProduitDTO produitDTO);

    ProduitDTO toProduitDto(Produit produit);
}
