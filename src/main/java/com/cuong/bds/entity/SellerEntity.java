package com.cuong.bds.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Entity
@Data
@Table
public class SellerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    private List<HouseEntity> houses;
}
