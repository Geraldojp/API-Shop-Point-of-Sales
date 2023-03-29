package com.example.springecommerce.Model.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "stock")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "total_stock")
    private Integer totalStock;

    @OneToOne(mappedBy = "stock", cascade = CascadeType.ALL, orphanRemoval = true)
    private Product product;

    @Override
    public String toString() {
        return totalStock.toString();
    }
}