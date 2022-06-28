package com.gonggu.site.mypage.controller;

import com.gonggu.site.Login.model.UserDto;
import com.gonggu.site.aspect.TokenRequired;
import com.gonggu.site.board.dto.BoardDto;
import com.gonggu.site.config.SecurityService;
import com.gonggu.site.mypage.service.MyPageService;
import com.gonggu.site.participation.dto.ParticipationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("mypage")
@TokenRequired
public class MyPageController {

    @Autowired
    SecurityService securityService;

    @Autowired
    MyPageService mypageService;


    //내가 쓴 글 조회
    public List<BoardDto> getMyPost(HttpServletRequest request){
        return mypageService.getMyPost(securityService.getIdAtToken(request), 0);
    }

    @PostMapping("")
    //내 비밀번호 일치 확인
    //토큰으로 아이디 받고, 바디로 패스워드 받음
    public Boolean checkMyPw(HttpServletRequest request, @RequestBody UserDto userDto ){
        return mypageService.checkMyPw(securityService.getIdAtToken(request), userDto.getPassword());
    }

    //공구 참여 현황 리스트 조회
    public List<BoardDto> getMyJoinList(HttpServletRequest request){
        List<Integer> boardIdList = mypageService.getBoardID(securityService.getIdAtToken(request));
        return mypageService.getMyJoinList(boardIdList, 0);
    }


    //내 가입정보 조회
    public UserDto getMyInfo(HttpServletRequest request){
        return mypageService.getMyInfo(securityService.getIdAtToken(request));
    }

    ///GetMapping map함수로 묶어서 쏘기
    @GetMapping("")
    public Map<String, Object> getMypage(HttpServletRequest request){
        Map<String, Object> dbInfo = new HashMap<>();
        dbInfo.put("MyPost", getMyPost(request));
        dbInfo.put("MyJoinList", getMyJoinList(request));
        dbInfo.put("MyInfo", getMyInfo(request));
        return dbInfo;
    }
    @PutMapping("")
    public Boolean updateMyInfo(HttpServletRequest request, @RequestBody UserDto userDto){
        userDto.setId(securityService.getIdAtToken(request));

        String pw = mypageService.getMyPw(securityService.getIdAtToken(request));
        userDto.setPassword(pw);
        return mypageService.updateMyInfo(userDto);
    }

}
