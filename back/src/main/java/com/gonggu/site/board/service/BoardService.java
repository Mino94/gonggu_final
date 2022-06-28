package com.gonggu.site.board.service;

import com.gonggu.site.board.dto.BoardDto;

import java.util.List;

public interface BoardService {

    public BoardDto postBoard(BoardDto boardDto);

    BoardDto getDetail(int id);

    boolean deleteBoard(int id);

    BoardDto updateboard(BoardDto boardDto);

    public List<BoardDto> showBoard(Integer categoryId, String title);

}
