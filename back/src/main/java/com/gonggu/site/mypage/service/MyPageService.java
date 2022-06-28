package com.gonggu.site.mypage.service;

import com.gonggu.site.Login.model.UserDto;
import com.gonggu.site.board.dto.BoardDto;
import com.gonggu.site.participation.dto.ParticipationDto;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface MyPageService {
    List<BoardDto> getMyPost(Integer id, Integer del); //내가 쓴 글 불러오기

    Boolean checkMyPw(Integer id, String pw); //비밀번호 일치하는지 확인
    List<Integer> getBoardID(Integer id); //내가 참여한 공구 board_id 불러오기

    List<BoardDto> getMyJoinList(List<Integer> board_id, Integer del); //board_id를 통해 게시글 가져오기

    UserDto getMyInfo(Integer id); //내 개인정보 불러오기

    Boolean updateMyInfo(UserDto userDto);
    String getMyPw(Integer id);
}
