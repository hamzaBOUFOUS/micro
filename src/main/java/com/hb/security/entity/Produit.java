package com.hb.security.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "unit_price")
    private float unitPrice;
    @Column(name = "product_active")
    private boolean active=true;
    @Column(name = "units_in_stock")
    private int unitsInStock;
    @Column(name = "date")
    private LocalDate dateCreatedProdut;
    @Column(name="traking")
    private String reference;
}
