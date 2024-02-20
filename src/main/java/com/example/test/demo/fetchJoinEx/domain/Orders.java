package com.example.test.demo.fetchJoinEx.domain;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Order 예약어-> Orders 사용
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id")
    private int id;

    private String itemNm;

    //Member정보 지연로딩 설정.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="mem_id")
    private Member member;





}
