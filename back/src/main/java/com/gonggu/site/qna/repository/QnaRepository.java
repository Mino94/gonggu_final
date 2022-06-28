package com.gonggu.site.qna.repository;

import com.gonggu.site.qna.model.QnaDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QnaRepository extends JpaRepository<QnaDto, Integer> {
    QnaDto save(QnaDto qnaDto);
    QnaDto findById(int id);

    List<QnaDto> findAll();
//    void deleteById(int id);
}
