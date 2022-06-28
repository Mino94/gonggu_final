package com.gonggu.site.board.repository;

import com.gonggu.site.board.dto.BoardDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository extends JpaRepository<BoardDto, Long> {
    BoardDto save(BoardDto boardDto);

    BoardDto findByIdAndDel(int id, int del);

    //List<BoardDto> findAll();  //게시물의 모든 데이터 가지고 오는 것

    List<BoardDto> findByCategoryIdAndTitleContaining(Integer categoryId, String title);

    List<BoardDto> findByTitleContainingAndDel(String title, int del);

    List<BoardDto> findByCategoryIdAndTitleContainingOrderByRegDateDesc(Integer categoryId, String title);

    List<BoardDto> findByTitleContainingAndDelOrderByRegDateDesc(String title, int del);
  
    List<BoardDto> findByTitleContaining(String title);

    //mypage mypost
    List<BoardDto> findByUserIdAndDelOrderByRegDateDesc(Integer userId, Integer del);

    List<BoardDto> findByIdInAndDelOrderByRegDateDesc(List<Integer> id, Integer del);

}
