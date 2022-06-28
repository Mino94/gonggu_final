package com.gonggu.site.category.model;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data //lombok : getter setter constructor 생성
@Entity //jpa와 매핑하기 위해 선언
@EntityListeners(AuditingEntityListener.class)//CreatedDate 를 사용하기 위해 필요
@Table(name="CATEGORY") //테이블명과 변수명이 다를 때 테이블명을 지정해 주어야 한다.
@DynamicInsert //Insert시 null인 컬럼 제외( default 로 지정해놓은 값이 들어가게끔 유도하기 위해 사용 )
@DynamicUpdate
public class CategoryDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

}
