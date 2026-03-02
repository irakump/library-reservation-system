import { useReservationContext } from "../contexts/ReservationContext";
import BookDataPage from "./BookDataPage";

const ReservationPage = () => {
  const { reservations } = useReservationContext();
  console.log(reservations);

  return (
    <>
      {reservations && (
        <BookDataPage
          title="My Reservations"
          books={reservations}
          pageType="reservation"
        />
      )}
    </>
  );
};

export default ReservationPage;
