package com.gonggu.site.qna.service;

import com.gonggu.site.qna.repository.QnaRepository;
import com.gonggu.site.qna.model.QnaDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class QnaServiveImpl implements QnaService{
    @Autowired
    private QnaRepository qnaRepository;

    @Override
    public QnaDto insertQna(QnaDto qnaDto){
        return qnaRepository.save(qnaDto);
    }
    @Override
    public List<QnaDto> getQnaAll(){
        return qnaRepository.findAll();
    }
    @Override
    public QnaDto getQnaOne(int id) {
        return qnaRepository.findById(id);
    }

    @Override
    public QnaDto updateQna(int id, String question) {
        QnaDto qnaDto = getQnaOne(id);
        qnaDto.setQuestion(question);
        qnaDto.setQuestionDate(LocalDateTime.now());
        return qnaRepository.save(qnaDto);
    }
    @Override
    public void deleteQna(int id) {
        qnaRepository.deleteById(id);
    }

    @Override
    public QnaDto insertAnswer(QnaDto qnaDto) {
        qnaDto.setAnswerDate(LocalDateTime.now());
        return qnaRepository.save(qnaDto);
    }

    @Override
    public List<QnaDto> getAnswerAll() {
        return qnaRepository.findAll();
    }

    @Override
    public QnaDto getAnswerOne(int id) {
        return qnaRepository.findById(id);
    }

    @Override
    public QnaDto updateAnswer(int id, String answer) {
        QnaDto qnaDto = getAnswerOne(id);
        qnaDto.setAnswer(answer);
        qnaDto.setQuestionDate(LocalDateTime.now());
        return qnaRepository.save(qnaDto);
    }

//    @Override
//    public void deleteAnswer(int id) {
//        qnaRepository.deleteById(id);
//    }

}
