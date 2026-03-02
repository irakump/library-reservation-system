import api from "./axiosConfig";

export function createReservation(userId, isbn) {
  return api
    .post(`/reservations/new`, {
      userId,
      isbn,
    })
    .then((response) => {
      console.log("create res: ", response);
      return response.data;
    })
    .catch((error) => {
      console.log(error);
      throw error;
    });
}

export function cancelReservation(userId, isbn, reservationId) {
  return api
    .put(`/reservations/cancel`, {
      userId,
      isbn,
      reservationId,
    })
    .then((response) => {
      console.log("cancel: ", response);
      return response.data;
    })
    .catch((error) => {
      console.error(error);
      throw error;
    });
}

export const getReservations = async (userId) => {
  return await api.get(`/reservations/user/${userId}`);
};
