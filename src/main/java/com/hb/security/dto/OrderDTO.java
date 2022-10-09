package com.hb.security.dto;

import com.hb.security.entity.OrderItem;
import com.hb.security.entity.Produit;
import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private List<OrderItem> items;
}
