import React, { useState, useEffect } from "react";
import QnaReply from "./QnaReply";
import { Button, Form } from "react-bootstrap";
import { useDispatch } from "react-redux";
import { answerDelete } from "../../../redux/qna/answer";
import { useNavigate, useParams } from "react-router-dom";

const QnaListItem = ({ qnaValue, checkId, board }) => {
  const { id, boardId, questionId, question, answer } = qnaValue;

  const [visible, setVisible] = useState(false);
  const dispatch = useDispatch();

  const navigate = useNavigate();

  const handleSubmit = (e) => {
    dispatch(answerDelete({ id: id }));
    alert("삭제 되었습니다.");
  };
  const nowId = localStorage.getItem("id");

  return boardId === parseInt(checkId) ? (
    <>
      <div className="question_content">
        {question}
        {board.data.get("userId") === nowId ? (
          <Button
            type="button"
            className="answerBtn"
            variant="success"
            onClick={() => {
              setVisible(!visible);
            }}
          >
            {visible ? "취소" : "답변"}
          </Button>
        ) : null}
      </div>
      {answer === null ? (
        <div className="hide_answer_form">
          {visible && <QnaReply index={id} />}
        </div>
      ) : (
        <div className="answer_content">
          {answer}
          <Button
            type="button"
            variant="success"
            className="answerBtn"
            onClick={handleSubmit}
          >
            삭제
          </Button>
        </div>
      )}
    </>
  ) : null;
};

export default QnaListItem;
