package com.hb.security.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {
    private int quantity;
    private long order;
    private long produit;
}
