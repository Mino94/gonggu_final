package com.gonggu.site.qna.model;

import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data //lombok : getter setter constructor 생성
@Entity //jpa와 매핑하기 위해 선언
@EntityListeners(AuditingEntityListener.class)//CreatedDate 를 사용하기 위해 필요
@Table(name="QNA") //테이블명과 변수명이 다를 때 테이블명을 지정해 주어야 한다.
@DynamicInsert //Insert시 null인 컬럼 제외( default 로 지정해놓은 값이 들어가게끔 유도하기 위해 사용 )
@Component
public class QnaDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    @Column(name="BOARD_ID", nullable = false)
    private Integer boardId;
    @Column(name="QUESTION_ID",nullable = false)
    private Integer questionId;
    @Column(name="ANSWER_ID")
    private Integer answerId;

    @Column(name="QUESTION",length = 300, nullable = false)
    private String question;

    @Column(name="ANSWER", length = 300)
    private String answer;

    @Column(name="QUESTION_DATE")
    @CreatedDate
    private LocalDateTime questionDate;

    @Column(name="ANSWER_DATE")
    private LocalDateTime answerDate;

}
