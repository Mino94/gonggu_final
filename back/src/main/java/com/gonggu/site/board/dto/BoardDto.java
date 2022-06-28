package com.gonggu.site.board.dto;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data //lombok : getter setter constructor 생성
@Entity //jpa와 매핑하기 위해 선언
@EntityListeners(AuditingEntityListener.class)//CreatedDate 를 사용하기 위해 필요
@Table(name="BOARD") //테이블명과 변수명이 다를 때 테이블명을 지정해 주어야 한다.
@DynamicInsert //Insert시 null인 컬럼 제외( default 로 지정해놓은 값이 들어가게끔 유도하기 위해 사용 )
@DynamicUpdate
public class BoardDto {

    @Id //테이블에 id에 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) //테이블에 지정해놓은 방식으로 키값 자동생성
    private Integer id;

    private String title;

    private String content;

    @Column(name="end_date") //필드명이 변수명과 다를 때 필드명을 지정해 주어야 한다.
    private String endDate;

    @Column(name="reg_date", updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @CreatedDate
    private LocalDateTime regDate;

    @Column(name="user_id")
    private Integer userId;

    @Column(name="category_id")
    private Integer categoryId;

    @Column(name="max_count")
    private Integer maxCount;

    @Column(name="current_count")
    private Integer currentCount;

    private Integer price;

    private String img;

    @Column(name="img_name")
    private String imgName;

    private Integer del;


}
