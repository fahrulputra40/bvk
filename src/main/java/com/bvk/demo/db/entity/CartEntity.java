package com.bvk.demo.db.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "tb_cart")
public class CartEntity {

    @Id
    private String id;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "tb_cart_item",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private List<ItemEntity> itemEntity;
}
