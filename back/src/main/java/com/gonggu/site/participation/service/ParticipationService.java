package com.gonggu.site.participation.service;

import com.gonggu.site.Login.model.UserDto;
import com.gonggu.site.participation.dto.ParticipationDto;

import java.util.List;

public interface ParticipationService {
    List<UserDto> getAll(int id);

    boolean insertOne(ParticipationDto participationDto);

    boolean deleteOne(int id, int userId);

    boolean checkJoinState(int boardId, int userId);

}
