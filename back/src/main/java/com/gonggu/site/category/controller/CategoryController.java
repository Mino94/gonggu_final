package com.gonggu.site.category.controller;

import com.gonggu.site.board.dto.BoardDto;
import com.gonggu.site.board.service.BoardService;
import com.gonggu.site.category.model.CategoryDto;
import com.gonggu.site.category.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;


    @GetMapping("")
    public List<CategoryDto> category() {
        System.out.println(categoryService.postCategory());
        return categoryService.postCategory();
    }
}
