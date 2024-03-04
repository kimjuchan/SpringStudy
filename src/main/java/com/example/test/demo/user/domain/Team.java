package com.example.test.demo.user.domain;


import com.example.test.demo.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Team extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String teamNm;

    @OneToMany(mappedBy = "team")
    private List<Account> accountList;

    public void changeAccount(Account account){
        this.accountList.add(account);
    }


}
