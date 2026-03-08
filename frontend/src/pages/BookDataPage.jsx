import BookCard from "../components/Books/BookCard.jsx";
import { useState } from "react";
import BookModal from "../components/Books/BookModal.jsx";
import { useLoanContext } from "../contexts/LoanContext.jsx";

const BookDataPage = ({ title, books, pageType }) => {
  const [open, setOpen] = useState(null); //passes bookobject
  const { addToLoans } = useLoanContext();

  return (
    <>
      <div className="w-full">
        <h1
          className={`text-2xl font-bold text-center text-heading ${pageType === "favourite" ? "py-0" : "py-6 sm:py-8"}`}
        >
          {title}
        </h1>
        <div className="grid grid-cols-1 md:grid-cols-2 bg-profileBackground p-4 sm:p-6 gap-4 sm:gap-6 rounded-md">
          {books.map((book) => (
            <BookCard
              key={book.isbn}
              book={book}
              pageType={pageType}
              setOpen={setOpen}
              addToLoans={addToLoans}
            />
          ))}
        </div>
        {open && (
          <BookModal
            book={open}
            pageType={pageType}
            setOpen={setOpen}
            addToLoans={addToLoans}
          ></BookModal>
        )}
      </div>
    </>
  );
};
export default BookDataPage;
