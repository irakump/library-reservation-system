import api from "./axiosConfig";

// Get user by ID (protected endpoint)
export const getUserById = (userId) => api.get(`/users/${userId}`);

// Get all users (protected endpoint)
export const getAllUsers = () => api.get("/users");

// Get user favorites
export const getUserFavorites = (userId) =>
  api.get(`/users/${userId}/favorites`);
