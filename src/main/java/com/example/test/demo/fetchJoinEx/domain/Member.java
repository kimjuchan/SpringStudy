package com.example.test.demo.fetchJoinEx.domain;


import com.example.test.demo.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "mem_id")
    private int id;

    private String userNm;

    @OneToMany
    @JoinColumn(name = "order_id")
    private List<Orders> orders;




}
