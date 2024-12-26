package com.springbootmvc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // Auto-increment the primary key
    private Long id; 
    private String name;
    private String brand;
    private String madeIn;
    private double price;
    private int quantity;
    @Column(name = "dr")
    private double discountRate;
    private double taxPrice;
    private double discountPrice;
    private double offerPrice;
    private double finalPrice;
    private double stockValue;
	
}

