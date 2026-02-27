import axios from "axios";

// This is actually authService between frontend and backend

const AUTH_API_BASE_URL = "http://localhost:8081/api/auth";

export const registerAPICall = (registerObj) =>
  axios.post(AUTH_API_BASE_URL + "/register", registerObj);

export const loginAPICall = (loginObj) =>
  axios.post(AUTH_API_BASE_URL + "/login", loginObj);
