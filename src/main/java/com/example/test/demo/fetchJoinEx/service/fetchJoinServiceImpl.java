package com.example.test.demo.fetchJoinEx.service;

import org.springframework.stereotype.Service;

@Service
public class fetchJoinServiceImpl implements fetchJoinService{

    /**
     * Persistence Context
     * Dirty Checking : 상태 변경 검사 (원본 스냅샷과 비교 후 반영)
     * CASACDE (영속성 전이)   ALL, PERSIST, REMOVE  + orphanRemoval=true 설정(Remove를 통해서 발생된 고아 객채에 대해서 자동으로 삭제해주는 옵션)
     * -> Remove를 통해서 부모 객체만 삭제되는 경우 자식 객체에 대해서 삭제처리 진행.
     * Fetch join
     * JPQL에서 제공됨. N+1 문제 해결가능.
     * 페이징 쿼리 사용 불가.
     * QueryBuilder >> QueryDSL
     * @ManyToMany 상황에서 중간 엔티티 생성 기준은 정규화를 통해서....
     * 제 1 > 각 컬럼 하나의 속성 값 AND Unique 값 AND 같은 TYPE
     * 제 2 > 부분적 종속이 없어져야함.(모든 컬럼이 완전 함수 종속 만족) **부분 함수 종속성은 기본키가 2개 이상의 컬럼으로 구성될 경우임  하나의 컬럼을 기본키로 가지고 있으면 생략.
     * 제 3 > 기본키를 제외한 속성들 간의 이행 종속성이 없어야함.
     * BCNF > 모든 결정자가 후보키 집합에 속함.
     *
     */







}
