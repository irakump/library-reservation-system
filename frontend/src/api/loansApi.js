import axios from "axios";

const URL = import.meta.env.VITE_API_LOANS;

export const getLoanByUserId = async (userID) => {
    const response = await axios.get(`${URL}/user/${userID}`);
    return response.data;
}
