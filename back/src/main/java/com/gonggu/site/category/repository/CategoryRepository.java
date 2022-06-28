package com.gonggu.site.category.repository;

import com.gonggu.site.category.model.CategoryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryDto, Long> {

    List<CategoryDto> findAllByOrderByIdAsc();
}
