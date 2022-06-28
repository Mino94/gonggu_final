package com.gonggu.site.board.service;

import com.gonggu.site.board.dto.BoardDto;
import com.gonggu.site.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import java.sql.Blob;

@Service
public class BoardServiceimpl implements BoardService {

    @Autowired
    BoardRepository boardRepository;

    @Override
    public BoardDto postBoard(BoardDto boardDto) {

        return boardRepository.save(boardDto);
    }

    @Override
    public BoardDto getDetail(int id) {
        BoardDto boardDto = boardRepository.findByIdAndDel(id, 0);
        return boardDto;
    }

    @Override
    public boolean deleteBoard(int id) {
        boolean isSuccess = false;
        BoardDto boardDto = boardRepository.findByIdAndDel(id, 0);

        if(boardDto == null)
            return isSuccess;

        boardDto.setDel(1);
        isSuccess = boardRepository.save(boardDto) != null;

        return isSuccess;
    }

    @Override
    public BoardDto updateboard(BoardDto boardDto) {
        if (boardDto.getImgName() == null) {
            BoardDto originalBoardDto = boardRepository.findByIdAndDel(boardDto.getId(), 0);
            boardDto.setImg(originalBoardDto.getImg());
            boardDto.setImgName(originalBoardDto.getImgName());
            boardDto.setRegDate(originalBoardDto.getRegDate());
            boardDto.setUserId(originalBoardDto.getUserId());
            boardDto.setDel(originalBoardDto.getDel());
        }
        boardDto = boardRepository.save(boardDto);
        return boardDto;
    }
    @Override
    public List<BoardDto> showBoard(Integer categoryId, String title) {

        //return boardRepository.findAll();
        System.out.println("cate"+ categoryId);
        if(categoryId != null) {
            String reTitle = title==null? "": title;
            return boardRepository.findByCategoryIdAndTitleContainingOrderByRegDateDesc(categoryId, reTitle); //categoryId가 있을경우
        } else {
            return boardRepository.findByTitleContainingAndDelOrderByRegDateDesc(title, 0); //categoryId가 없을 경우
        }
    }
}
