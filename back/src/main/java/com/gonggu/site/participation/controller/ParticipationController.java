package com.gonggu.site.participation.controller;

import com.gonggu.site.Login.model.UserDto;
import com.gonggu.site.aspect.TokenRequired;
import com.gonggu.site.board.dto.BoardDto;
import com.gonggu.site.board.service.BoardService;
import com.gonggu.site.config.SecurityService;
import com.gonggu.site.participation.dto.ParticipationDto;
import com.gonggu.site.participation.service.ParticipationService;
import com.gonggu.site.token.controller.MemberTokenController;
import com.gonggu.site.token.service.MemberTokenService;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/participation")
public class ParticipationController {

    @Autowired
    ParticipationService participationService;

    @Autowired
    BoardService boardService;

    @Autowired
    SecurityService securityService;

    @Autowired
    MemberTokenService memberTokenService;

    @Autowired
    MemberTokenController memberTokenController;

    @GetMapping("/{boardId}/all")
    public List<UserDto> getAll(@PathVariable int boardId) {
        return participationService.getAll(boardId);
    }

    @PostMapping("/{boardId}")
    public boolean insertOne(HttpServletRequest request, @PathVariable int boardId) {
        ParticipationDto participationDto = new ParticipationDto();
        participationDto.setMemberId(securityService.getIdAtToken(request));
        participationDto.setBoardId(boardId);

        boolean result = false;
        try {
            result = participationService.insertOne(participationDto);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return false;
        }

        BoardDto boardDto = boardService.getDetail(boardId);
        if(boardDto.getCurrentCount().equals(boardDto.getMaxCount())) {
            String token = memberTokenService.getMemberToken(boardDto.getUserId());
            memberTokenController.sendMessage(token, "등록하신 모집이 마감되었습니다.");
        }

        return result;
    }

    @DeleteMapping("/{boardId}")
    public boolean deleteOne(HttpServletRequest request, @PathVariable int boardId) {
        int userId = securityService.getIdAtToken(request);
        try {
            return participationService.deleteOne(boardId, userId);
        } catch(RuntimeException e) {
            e.printStackTrace();
            return false;
        }
    }

    @GetMapping("/{boardId}")
    @TokenRequired
    public boolean checkJoinState(HttpServletRequest request, @PathVariable int boardId) {
        int userId = securityService.getIdAtToken(request);
        return participationService.checkJoinState(boardId, userId);
    }

}
