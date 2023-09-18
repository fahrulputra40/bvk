package com.bvk.demo.db.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "item")
@Data
public class ItemEntity {

    @Id
    private String id;
    private String name;
    private String detail;
    private double price;
}
