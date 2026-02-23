import axios from "axios";

const URL = import.meta.VITE_API_base

export const getFavorites = async (uerId) => {
    const response = await axios.get(`${URL}/`)

}