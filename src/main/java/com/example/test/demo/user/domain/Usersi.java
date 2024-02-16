package com.example.test.demo.user.domain;


import com.example.test.demo.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Usersi extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mem_id")
    private int id;

    @Column
    //성별 : M , F
    private String memSex;
    @Column
    //결혼 여부 Y , N
    private String memMarried;
    @Column
    //차량 번호
    private String memPhoneNm;

    @Column
    private String memEmail;
}
