package com.hb.security.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hb.security.utils.Statu;
import lombok.*;
import org.hibernate.FetchMode;
import org.hibernate.annotations.Fetch;
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

    @Column(name = "active")
    private boolean active;

    @Column(name = "status")
    private Statu statu;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orders",fetch = FetchType.LAZY)
    private List<OrderItem> items;

}
