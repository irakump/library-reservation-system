//this components is rendered inside BookButton component which gets rendered inside BookModal and BookCard

import { useTranslation } from "react-i18next";
import { useReservationContext } from "../../contexts/ReservationContext";

function ReserveButton({ pageType, book, children }) {
  const { addToReservations, updateReservationStatus, reservations } =
    useReservationContext();
  const { t } = useTranslation("book_card");

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
      <p className="text-sm mb-1 text-left">{t("unavailable")}</p>
      <div>
        <button
          className="bg-actionButton font-semibold rounded-xl px-6 py-2 max-[200px]:px-2 max-[200px]:py-1.5 hover:bg-actionButtonHover float-right cursor-pointer"
          onClick={handleClick}
        >
          {children}
        </button>
      </div>
      
    </>
  );
}

export default ReserveButton;
