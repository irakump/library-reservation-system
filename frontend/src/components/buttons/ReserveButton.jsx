//this components is rendered inside BookButton component which gets rendered inside BookModal and BookCard

import { useTranslation } from "react-i18next";
import { useReservationContext } from "../../contexts/ReservationContext";
import { useSearchResult } from "../../contexts/SearchResultContext.jsx";
import { useSearchFilters } from "../../contexts/SearchFilterContext.jsx";
import PropTypes from "prop-types";

function ReserveButton({ pageType, book, children }) {
  const { addToReservations, updateReservationStatus } =
    useReservationContext();
  const { t } = useTranslation("book_card");
  const { fetchSearchResults } = useSearchResult();
  const { setSearchFilters } = useSearchFilters();

  // New reservation
  return pageType === "reservation" ? (
    <>
      <p className="text-sm mb-1 text-left rtl:text-right">
        {t("unavailable")}
      </p>
      <button
        className="bg-actionButton font-semibold rounded-xl px-6 py-2 max-[200px]:px-2 max-[200px]:py-1.5 hover:bg-actionButtonHover float-right rtl:float-left cursor-pointer"
        onClick={async (e) => {
          e.stopPropagation();
          await updateReservationStatus(book.reservationId);
          await setSearchFilters();
          await fetchSearchResults();
        }}
      >
        {children}
      </button>
    </>
  ) : (
    <>
      <p className="text-sm mb-1 text-left rtl:text-right">
        {t("unavailable")}
      </p>
      <div>
        <button
          className="bg-actionButton font-semibold rounded-xl px-6 py-2 max-[200px]:px-2 max-[200px]:py-1.5 hover:bg-actionButtonHover float-right rtl:float-left cursor-pointer"
          onClick={async (e) => {
            e.stopPropagation();
            await addToReservations(book.isbn);
            await fetchSearchResults();
          }}
        >
          {children}
        </button>
      </div>
    </>
  );
}

ReserveButton.propTypes = {
  pageType: PropTypes.string.isRequired,
  book: PropTypes.shape({
    isbn: PropTypes.string.isRequired,
    reservationId: PropTypes.number,
  }).isRequired,
  children: PropTypes.node.isRequired,
};
export default ReserveButton;
