package com.gonggu.site.qna.service;

import com.gonggu.site.qna.model.QnaDto;

import java.util.List;
import java.util.Optional;


public interface QnaService {
    QnaDto insertQna(QnaDto qnaDto);
    List<QnaDto> getQnaAll();
    QnaDto updateQna(int id, String question);
    QnaDto getQnaOne(int id);
    void deleteQna(int id);

    QnaDto insertAnswer(QnaDto qnaDto);
    List<QnaDto> getAnswerAll();
    QnaDto updateAnswer(int id, String question);
    QnaDto getAnswerOne(int id);
//    void deleteAnswer(int id);

}
