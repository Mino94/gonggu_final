package com.gonggu.site.category.service;

import com.gonggu.site.board.repository.BoardRepository;
import com.gonggu.site.category.model.CategoryDto;
import com.gonggu.site.category.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceimpl implements CategoryService{

    @Autowired
    CategoryRepository categoryRepository;


    @Override
    public List<CategoryDto> postCategory() {

        return categoryRepository.findAllByOrderByIdAsc();
    }
}
