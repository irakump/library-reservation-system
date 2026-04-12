import api from "./axiosConfig";
import i18n from "i18next";

const URL = "http://localhost:8081/api";

export const getFavorites = async (userId) => {
  return await api.get(`/users/${userId}/favorites/${i18n.language}`);
};

export const addFavorite = async (userId, isbn) => {
  const response = await api.post(`/users/${userId}/favorites/${isbn}`);
  return response;
};

export const removeFavorite = async (userId, isbn) => {
  return await api.delete(`/users/${userId}/favorites/${isbn}`);
};
