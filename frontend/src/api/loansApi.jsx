import api from "./axiosConfig";
import i18n from "../i18n.js";

export function createLoan(userId, isbn) {
  return api
    .post(`/loans/new`, {
      userId,
      isbn,
    })
    .then((response) => {
      //console.log("create res: ", response);
      return response.data;
    })
    .catch((error) => {
      console.log(error);
      throw error;
    });
}

export function returnLoan(userId, isbn, loanId) {
  return api
    .put(`/loans/return`, {
      userId,
      isbn,
      loanId,
    })
    .then((response) => {
      return response.data;
    })
    .catch((error) => {
      console.error(error);
      throw error;
    });
}

export const getLoans = async (userId) => {
  return await api.get(`/loans/user/${userId}/${i18n.language}`);
};

export async function getHistory(userId) {
  return await api.get(`/loans/user/${userId}/history/${i18n.language}`);
}
