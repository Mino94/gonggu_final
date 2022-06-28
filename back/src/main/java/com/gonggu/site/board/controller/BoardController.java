package com.gonggu.site.board.controller;

import com.gonggu.site.board.dto.BoardDto;
import com.gonggu.site.board.repository.BoardRepository;
import com.gonggu.site.board.service.BoardService;
import com.gonggu.site.config.SecurityService;
import com.gonggu.site.firestore.FireStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    FireStore fireStore;

    @Autowired
    BoardService boardService;

    @Autowired
    SecurityService securityService;


    @PostMapping("")
    public BoardDto write(HttpServletRequest request, @RequestBody(required = false) MultipartFile file, @ModelAttribute BoardDto boardDto) {
        int id = securityService.getIdAtToken(request);
        BoardDto result = null;
        try {
            if(file != null) {
                String url = fireStore.uploadFiles(file, file.getOriginalFilename());
                boardDto.setImg(url);
                boardDto.setImgName(file.getOriginalFilename());
            }
            boardDto.setUserId(id);
            result = boardService.postBoard(boardDto);
        } catch (IOException e) {
            System.err.println("[ERROR] " + e.getMessage());
        }
        return result;
    }

    @GetMapping("/{id}")
    public BoardDto read(@PathVariable int id) {
        return boardService.getDetail(id);
    }

    @DeleteMapping("/{id}")
    public boolean delete(@PathVariable int id) {
        return boardService.deleteBoard(id);
    }

    @PutMapping("/{id}")
    public BoardDto update(@RequestBody(required = false) MultipartFile file, @ModelAttribute BoardDto boardDto) {
        BoardDto result = null;

        try {
            if (file != null) {
                String url = fireStore.uploadFiles(file, file.getOriginalFilename());
                boardDto.setImg(url);
                boardDto.setImgName(file.getOriginalFilename());
            }
            result = boardService.updateboard(boardDto);
        } catch (IOException e) {
            System.err.println("[ERROR] " + e.getMessage());
        }

        return result;
    }

    @GetMapping("")
    public List<BoardDto> show(@RequestParam String title, @RequestParam(required = false) Integer categoryId) {
        System.out.println("title : " + title);
        List<BoardDto> temp = boardService.showBoard(categoryId, title);
        return boardService.showBoard(categoryId, title);
    }
}

