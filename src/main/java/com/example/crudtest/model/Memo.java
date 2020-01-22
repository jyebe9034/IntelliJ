package com.example.crudtest.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "memo")
@Data
public class Memo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(length = 30, nullable = false)
    private String title;

    @Column(length = 20, nullable = false)
    private String writer;
}
