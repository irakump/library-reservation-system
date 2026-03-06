//this components is rendered inside BookButton component which gets rendered inside BookModal and BookCard

import { useReservationContext } from "../../contexts/ReservationContext";

function ReserveButton({ pageType, book, children }) {
  const { addToReservations, updateReservationStatus, reservations } =
    useReservationContext();

  // Check active reservation
  const activeReservation = reservations.find(
    (r) => r.bookIsbn === (book.isbn ?? book.bookIsbn) && r.status === "active",
  );

  const handleClick = (e) => {
    e.stopPropagation();
    if (pageType === "reservation" && activeReservation) {
      updateReservationStatus(activeReservation.reservationId);
    } else {
      const isbn = book.isbn ?? book.bookIsbn; // If book.isbn is null, use book.bookIsbn
      addToReservations(isbn);
    }
  };

  // New reservation
  return (
    <>
      <p className="text-sm mb-1 text-left"> 🔴 Not available</p>
      <div>
        <button
          className="bg-filter font-semibold rounded-xl px-6 py-2 hover:bg-sky-500 float-right cursor-pointer"
          onClick={handleClick}
        >
          {children}
        </button>
      </div>
        
      
    </>
  );
}

export default ReserveButton;
