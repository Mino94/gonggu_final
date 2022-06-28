import axios from "axios";

export const insertQnaApi = (params) => {
  console.log("insertQnaApi", params);
  return axios.post("http://localhost:8080/qna", params);
};

export const selectQnaApi = () => {
  return axios.get("http://localhost:8080/qna");
};

export const insertAnswerApi = (params) => {
  console.log("insertAnswerApi params", params);
  return axios.put("http://localhost:8080/qna/answer", params);
};

export const deleteAnswerApi = (params) => {
  console.log("deleteAnswerApi params", params);
  return axios.put("http://localhost:8080/qna/answer", params);
};
