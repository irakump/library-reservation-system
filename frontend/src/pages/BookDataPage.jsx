import BookCard from "../components/Books/BookCard.jsx";
import { useState } from "react";
import BookModal from "../components/Books/BookModal.jsx";
import {useSearchResult} from "../contexts/SearchResultContext.jsx";
import {useLoanContext} from "../contexts/LoanContext.jsx";
import {useFavoritesContext} from "../contexts/FavoritesContext.jsx";
import {useReservationContext} from "../contexts/ReservationContext.jsx";

const BookDataPage = ({ title, books, pageType }) => {
  const [open, setOpen] = useState(null); //passes bookobject
    const {searchResults} = useSearchResult();
    const {loans, history} = useLoanContext();
    const {favorites} = useFavoritesContext()
    const {reservations} = useReservationContext();
    let openBook;

    if (pageType === "home") {
        openBook = open ? searchResults.find((b) => b.isbn === open) ?? loans.find((b) => b.isbn === open) ?? favorites.find((b) => b.isbn === open) ?? reservations.find(b => b.isbn === open) ?? history.find(b => b.isbn === open): null;
    } else {
        openBook = open ? books.find((bk) => bk.isbn === open) : null;
    }


  return (
    <div className="w-full min-h-screen">
      <h1
        className={`text-2xl font-bold text-center text-heading ${pageType === "favourite" || pageType === "home" ? "py-0" : "py-6 sm:py-8"}`}
      >
        {title}
      </h1>
      <div className="grid grid-cols-1 md:grid-cols-2 bg-profileBackground p-4 sm:p-6 gap-4 sm:gap-6 sm:rounded-md">
        {books.map((book) => (
          <BookCard
            key={book.isbn}
            book={book}
            pageType={pageType}
            setOpen={(book) => setOpen(book.isbn)}
          />
        ))}
      </div>
      {open && openBook &&(
        <BookModal
          book={openBook}
          pageType={pageType}
          setOpen={setOpen}
        ></BookModal>
      )}
    </div>
  );
};
export default BookDataPage;
