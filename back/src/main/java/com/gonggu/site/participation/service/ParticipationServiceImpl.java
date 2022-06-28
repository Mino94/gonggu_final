package com.gonggu.site.participation.service;

import com.gonggu.site.Login.model.UserDto;
import com.gonggu.site.Login.repository.UserRepository;
import com.gonggu.site.board.dto.BoardDto;
import com.gonggu.site.board.repository.BoardRepository;
import com.gonggu.site.participation.dto.ParticipationDto;
import com.gonggu.site.participation.repository.ParticipationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ParticipationServiceImpl implements ParticipationService{

    @Autowired
    ParticipationRepository participationRepository;

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<UserDto> getAll(int id) {
        List<ParticipationDto> participationList = participationRepository.findAllByBoardId(id);
        System.out.println(participationList);
        List<Integer> userIdList = new ArrayList<>();
        for(ParticipationDto participationDto : participationList) {
            userIdList.add(participationDto.getMemberId());
        }
        System.out.println(userIdList);
        List<UserDto> userList = userRepository.findByIdIn(userIdList);
        System.out.println(userList);
        userList.forEach(u -> u.setPassword(null));
        return userList;
    }

    @Override
    @Transactional
    public boolean insertOne(ParticipationDto participationDto) {
        try {
            if (participationRepository.findByBoardIdAndMemberId(participationDto.getBoardId(), participationDto.getMemberId()) != null) {
                throw new RuntimeException("이미 등록되어 있습니다.");
            }

            if (participationRepository.save(participationDto) == null) {
                throw new RuntimeException("참여 실패");
            }

            BoardDto boardDto = boardRepository.findByIdAndDel(participationDto.getBoardId(), 0);
            if (boardDto == null) {
                throw new RuntimeException("없는 게시글");
            }

            boardDto.setCurrentCount(boardDto.getCurrentCount() + 1);
            if (boardRepository.save(boardDto) == null) {
                throw new RuntimeException("저장 실패");
            }
        }catch (RuntimeException e) {
            e.printStackTrace();
            System.out.println("[예외 발생] " + e.getMessage());
            throw e;
        }
        return true;
    }

    @Override
    @Transactional
    public boolean deleteOne(int id, int userId) {
        try {
            if(participationRepository.deleteByBoardIdAndMemberId(id, userId) == 0) {
                throw new RuntimeException("공구 참여 데이터에 없음");
            }

            BoardDto boardDto = boardRepository.findByIdAndDel(id, 0);
            if(boardDto == null) {
                throw new RuntimeException("없는 게시글");
            }

            boardDto.setCurrentCount(boardDto.getCurrentCount() - 1);
            if(boardRepository.save(boardDto) == null) {
                throw new RuntimeException("저장 실패");
            }
        }catch(RuntimeException e) {
            e.printStackTrace();
            System.out.println("[예외 발생] " + e.getMessage());
            throw e;
        }
        return true;
    }

    @Override
    public boolean checkJoinState(int boardId, int userId) {
        ParticipationDto participationDto = participationRepository.findByBoardIdAndMemberId(boardId, userId);
        return participationDto != null;
    }
}
