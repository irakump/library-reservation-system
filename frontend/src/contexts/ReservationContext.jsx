import { createContext, useContext, useEffect, useState } from "react";
import { createReservation, cancelReservation, getReservations } from "../api/reservationsApi.js";
import { useAuth } from "./AuthContext.jsx";

const ReservationContext = createContext({});

export const useReservationContext = () => useContext(ReservationContext);

export const ReservationProvider = ({ children }) => {
  const [reservations, setReservations] = useState([]);
  const { user, isLoggedIn } = useAuth();

  useEffect(() => {
    // Only fetch if user is logged in
    if (isLoggedIn && user?.userId) {
      getReservations(user.userId)
        .then((res) => setReservations(res.data))
        .catch((error) => console.error("Error fetching reservations: ", error));
    }
  }, [isLoggedIn, user]);

  const addToReservations = async (isbn) => {
    if (!user?.userId) {
      console.error("User not logged in");
      return;
    }

    try {
      const response = await createReservation(user.userId, isbn); // Use JWT userId
        setReservations((prev) => [...prev, response]);
    } catch (error) {
      console.error("Error creating reservation: ", error);
    }
  };

const cancelReservation = async (userId, isbn, reservationId) => {
    try {
      await cancelReservation(userId, isbn, reservationId);
      setReservations(prev => prev.filter(f => f.reservationId !== reservationId));

    } catch (error) {
      console.error("Error cancelling reservation: ", error);
    }
  };

  const value = {
    addToReservations,
    cancelReservation,
    reservations,
  };

  return <ReservationContext.Provider value={value}>{children}</ReservationContext.Provider>;
};
