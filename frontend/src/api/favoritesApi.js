import axios from "axios";

const URL = "http://localhost:8081/api"

export const getFavorites = async (userId) => {
    return await axios.get(`${URL}/users/${userId}/favorites`);
}


export const addFavorite = async (userId, isbn) => {
    const response = await axios.post(`${URL}/users/${userId}/favorites/${isbn}`);
    return response;
}

export const removeFavorite = async (userId, isbn) => {
    return await axios.delete(`${URL}/users/${userId}/favorites/${isbn}`);
}