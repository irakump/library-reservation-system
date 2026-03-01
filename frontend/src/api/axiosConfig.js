import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:8081/api",
});

// Request interceptor - adds JWT token to every requiest
api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem("token");

    if (token) {
      config.headers["Authorization"] = `Bearer ${token}`;
      console.log("JWT token added to request: ", config.url);
    } else {
      console.log("No JWT token found for request: ", config.url);
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  },
);

// Response interceptor
api.interceptors.response.use(
  (response) => {
    return response;
  },
  (error) => {
    if (error.response?.status === 401) {
      console.error("Unauthorized! Token invalid or expired.");

      // Clear invalid token and user info from localstorage
      localStorage.removeItem("token");
      localStorage.removeItem("userId");
      localStorage.removeItem("email");
      localStorage.removeItem("nickname");
      localStorage.removeItem("role");

      // Redirect to home page
      window.location.href = "/";
    }
    return Promise.reject(error);
  },
);

export default api;
