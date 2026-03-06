import Button from "../buttons/Button.jsx";
import FavoriteButton from "../buttons/FavoriteButton.jsx";
import BookButtons from "./BookButtons.jsx";
import { getQueueLength } from "../../api/reservationsApi.js";
import { useEffect, useState } from "react";

const BookModal = ({ book, pageType, setOpen, addToLoans }) => {
  const [queueLength, setQueueLength] = useState(null);
  const isbn = pageType === "reservation" ? book.bookIsbn : book.isbn;

  useEffect(() => {
    if (book.availability === false) {
      getQueueLength(isbn)
        .then((res) => setQueueLength(res.data.queueLength))
        .catch(() => setQueueLength(null));
    }
  }, [isbn, book.availability]);

  return (
    <>
      <div
        className="fixed inset-0 flex items-start md:items-center justify-center bg-black/40"
        onClick={() => setOpen(null)}
      >
        <div
          className="relative w-full max-w-[465px] max-h-dvh md:max-h-[95dvh] overflow-y-auto bg-white rounded-xl p-6 sm:p-9 border-20 border-filter"
          onClick={(e) => e.stopPropagation()}
        >
          <button
            onClick={() => setOpen(null)}
            className="absolute top-0 right-4 text-xl"
          >
            ✕
          </button>
          <div className="flex flex-col">
            <div className="absolute cursor-pointer hover:text-red-700 sm:top-10 right-4 text-3xl">
              <FavoriteButton book={book}></FavoriteButton>
            </div>

            <div className="flex flex-col gap-4 ">
              <div className="flex flex-col-reverse sm:flex-row">
                <div className="w-36 sm:w-24 h-auto mt-1 mr-0.5 shrink-0 max-sm:self-center">
                  <img
                    src={`/books/${pageType === "reservation" ? book.bookIsbn : book.isbn}.jpg`}
                    alt={`Book image for ${book.title}`}
                    className="w-36 sm:w-24 h-auto rounded-sm outline-1 outline-gray-200"
                  />
                </div>
                <div className="max-sm:self-center">
                  <h1 className="font-bold text-lg text-left p-3 pt-0 pr-6 sm:pr-2 sm:ml-3">
                    {book.title}
                  </h1>
                </div>
              </div>

              <div className="flex-1">
                <p className="text-sm mb-1">
                  {book.authors
                    ?.map((author) => `${author.firstName} ${author.lastName}`)
                    .join(", ")}
                </p>
                <p className="text-sm mb-1">{book.year} </p>
                <p className="text-sm mb-1">{book.description}</p>
              </div>
            </div>
            <div className="mt-6 text-sm text-gray-700 space-y-1 capitalize"></div>
            <div className="mt-3 flex items-center gap-2 capitalize">
              <span className="px-3 py-1 text-xs rounded-full bg-gray-100">
                {book.language}
              </span>
              <span className="px-3 py-1 text-xs rounded-full bg-gray-100">
                {book.genre}
              </span>
            </div>
            <div className="mt-6 flex flex-row items-end justify-between">
              <div className="text-sm">
                {book.availability === false && queueLength !== null && (
                  <p>Queue length: {queueLength}</p>
                )}
              </div>
              {/* Button for loan/reserve/return/history data on history page */}
              <BookButtons pageType={pageType} book={book}></BookButtons>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};
export default BookModal;
