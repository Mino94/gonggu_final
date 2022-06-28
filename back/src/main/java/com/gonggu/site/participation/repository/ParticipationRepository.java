package com.gonggu.site.participation.repository;

import com.gonggu.site.participation.dto.ParticipationDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ParticipationRepository extends JpaRepository<ParticipationDto, Long> {

    List<ParticipationDto> findAllByBoardId(int id);

    ParticipationDto save(ParticipationDto participationDto);

    ParticipationDto findByBoardIdAndMemberId(int boardId, int memberId);

    @Transactional
    Integer deleteByBoardIdAndMemberId(int id, int userId);

    //mypage에서 공구참여 현황 확인 위한 board_id 조회
    List<ParticipationDto> findByMemberId(Integer memberId);
}
