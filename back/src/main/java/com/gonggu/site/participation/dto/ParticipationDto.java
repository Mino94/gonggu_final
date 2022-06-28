package com.gonggu.site.participation.dto;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="PARTICIPANTS")
public class ParticipationDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="board_id")
    private Integer boardId;

    @Column(name="member_id")
    private Integer memberId;

}
