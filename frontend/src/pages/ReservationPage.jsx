import { useReservationContext } from "../contexts/ReservationContext";
import BookDataPage from "./BookDataPage";

const ReservationPage = () => {
  const { reservations } = useReservationContext();
  //console.log(reservations);

  return (
    <div className="mx-auto px-4 max-w-md sm:max-w-4xl lg:max-w-6xl pb-12">
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
