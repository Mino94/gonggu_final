package com.gonggu.site.token.model;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Data
@Entity
@Table(name="MEMBER_TOKEN")
@DynamicInsert
public class MemberTokenDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(name="USER_ID")
    private Integer userId;

    private String token;

}
