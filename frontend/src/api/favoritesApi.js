import api from "./axiosConfig";

const URL = "http://localhost:8081/api";

export const getFavorites = async (userId) => {
  return await api.get(`/users/${userId}/favorites`);
};

export const addFavorite = async (userId, isbn) => {
  const response = await api.post(`/users/${userId}/favorites/${isbn}`);
  return response;
};

export const removeFavorite = async (userId, isbn) => {
  return await api.delete(`/users/${userId}/favorites/${isbn}`);
};
