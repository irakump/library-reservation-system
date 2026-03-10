import { useReservationContext } from "../contexts/ReservationContext";
import BookDataPage from "./BookDataPage";

const ReservationPage = () => {
  const { reservations } = useReservationContext();
  //console.log(reservations);

    if (reservations.length === 0) {
        return <div className="min-h-screen text-center p-10 bg-background">No reservations yet!</div>
    }

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
