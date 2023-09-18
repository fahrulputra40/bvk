package com.bvk.demo.db.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity(name = "cart")
public class CartEntity {

    @Id
    private String id;

    @OneToMany(fetch = FetchType.EAGER)
    private List<ItemEntity> itemEntity;
}
