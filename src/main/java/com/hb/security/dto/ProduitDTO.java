package com.hb.security.dto;

import lombok.*;

import javax.persistence.Column;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProduitDTO {
    private String name;
    private float unitPrice;
    private int unitsInStock;
    private String reference;
}
