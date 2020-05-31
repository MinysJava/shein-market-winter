package com.geekbrains.geekmarketwinter.entites;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "orders_item")
@Data
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

}
