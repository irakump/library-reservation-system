import api from "./axiosConfig";

export function createReservation(isbn) {
  return api
    .post(`/reservations/new`, { 
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

export function cancelReservation(reservationId) {
  return api
    .put(`/reservations/cancel`, {
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

export const getQueueLength = async (isbn) => {
  return await api.get(`/reservations/book/${isbn}/queue-length`);
};
