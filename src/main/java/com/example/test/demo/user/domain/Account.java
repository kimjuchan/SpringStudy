package com.example.test.demo.user.domain;


import com.example.test.demo.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Account extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String userNm;


    @ManyToOne(cascade = CascadeType.PERSIST)
    //@ManyToOne(fetch=FetchType.LAZY)
    //해당 team 정보는 읽기 전용으로 사용.  insertable , updatable  사용 시 해당 teamId 들어가지 않음 사용 주의해야함.
    @JoinColumn(name = "team_id", updatable = false)
    private Team team;

    //team,  team의 user정보 설정.
 /*   public void addTeam(Team team){
        this.setTeam(team);
        team.getAccountList().add(this);
    }

*/
}
