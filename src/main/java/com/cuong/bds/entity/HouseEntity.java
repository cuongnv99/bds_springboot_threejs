package com.cuong.bds.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table
public class HouseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;
    private double price;
    private String name;
    private String description;
    private String createDate;
    private String updateDate;
    private String houseType;
    @ManyToOne
    private SellerEntity seller;
}
