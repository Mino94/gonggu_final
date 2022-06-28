package com.gonggu.site.mypage.service;

import com.gonggu.site.Login.model.UserDto;
import com.gonggu.site.Login.repository.UserRepository;
import com.gonggu.site.aspect.TokenRequired;
import com.gonggu.site.board.dto.BoardDto;
import com.gonggu.site.board.repository.BoardRepository;
import com.gonggu.site.config.SecurityService;
import com.gonggu.site.participation.dto.ParticipationDto;
import com.gonggu.site.participation.repository.ParticipationRepository;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@DynamicUpdate
@Slf4j
public class MyPageServiceImpl implements MyPageService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ParticipationRepository participationRepository;

    @Override
    public List<BoardDto> getMyPost(Integer id, Integer del)
    {
        return boardRepository.findByUserIdAndDelOrderByRegDateDesc(id, del);
    }

    @Override
    public List<Integer> getBoardID(Integer id) {
        List<ParticipationDto> myParticipationList = participationRepository.findByMemberId(id);
        List<Integer> boardIdList = new ArrayList<>();
        for(ParticipationDto myParticipation : myParticipationList) {
            boardIdList.add(myParticipation.getBoardId());
        }
        return boardIdList;
    }
    @Override
    public List<BoardDto> getMyJoinList(List<Integer> board_id, Integer del) {
        return boardRepository.findByIdInAndDelOrderByRegDateDesc(board_id, del);
    }////

    @Override
    public UserDto getMyInfo(Integer id) {
        UserDto temp = userRepository.findById(id);
        temp.setPassword(null);
        return temp;
    }

    @Override
    public Boolean checkMyPw(Integer id, String pw) {
        UserDto userDto = userRepository.findById(id);
        String dbPw = userDto.getPassword();
        if(dbPw.equals(pw))
            return true;
        else
            return false;
    }


    @Override
    public Boolean updateMyInfo(UserDto userDto) {
        if(userRepository.save(userDto) == null) {
            return false;
        }else {
            return true;
        }
    }

    @Override
    public String getMyPw(Integer id){
        return userRepository.findById(id).getPassword();
    }


}

