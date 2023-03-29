package com.example.springecommerce.Model.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table(name = "price")
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "price")
    private Double price;

    @OneToOne(mappedBy = "price", cascade = CascadeType.ALL, orphanRemoval = true)
    private Product product;

    @Override
    public String toString() {
        return String.valueOf(price);
    }
}