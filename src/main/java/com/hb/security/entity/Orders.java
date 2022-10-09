package com.hb.security.entity;

import com.hb.security.utils.Statu;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "date_created")
    private LocalDateTime dateCreatedOrder;

    @Column(name = "reference")
    private String reference;

    @Column(name = "totale_ttc")
    private float totaleTTC;

    @Column(name = "totale_hors_tax")
    private float totaleHorsTax;

    @Column(name = "active")
    private boolean active;

    @Column(name = "status")
    private Statu statu;

    @Column(name = "validation")
    private boolean validation;

    @OneToMany(mappedBy="order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderItem> items;
    /*@ManyToMany()
    @JoinTable(name = "order_item",
            joinColumns = {@JoinColumn(name = "order_id")},
            inverseJoinColumns = {@JoinColumn(name = "produit_id")})
    private List<Produit> produits;*/

}
