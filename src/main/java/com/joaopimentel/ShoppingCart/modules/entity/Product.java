package com.joaopimentel.ShoppingCart.modules.entity;

import com.joaopimentel.ShoppingCart.configuration.AuditModel;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Product extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(fetch = FetchType.LAZY,
            mappedBy = "products")
    private Set<ShoppingList> shoppingList;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;
}
