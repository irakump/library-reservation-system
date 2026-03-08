import { useReservationContext } from "../contexts/ReservationContext";
import BookDataPage from "./BookDataPage";

const ReservationPage = () => {
  const { reservations } = useReservationContext();
  //console.log(reservations);

  return (
    <div className="mx-auto px-4">
      {reservations && (
        <BookDataPage
          title={`My Reservations (${reservations.length})`}
          books={reservations}
          pageType="reservation"
        />
      )}
    </div>
  );
};

export default ReservationPage;
