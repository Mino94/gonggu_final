package com.gonggu.site.Login.model;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name="MEMBER")
@DynamicInsert
public class UserDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "USER_ID")
    private String userId;
    private String password;
    private String name;
    @Column(name = "POST_CODE")
    private String postcode;
    private String address1;
    private String address2;
    private String address3;
    private String tel;
    private String bank;
    @Column(name="BANK_ACCOUNT")
    private String bankaccount;

}
