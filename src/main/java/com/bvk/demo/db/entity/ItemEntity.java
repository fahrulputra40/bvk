package com.bvk.demo.db.entity;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "tb_item")
@Data
public class ItemEntity {

    @Id
    private String id;
    private String name;
    private String detail;
    private double price;
}
