package com.hb.security.dto;

import com.hb.security.entity.Produit;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private int quantity;
    private List<Produit> produitList;
}
