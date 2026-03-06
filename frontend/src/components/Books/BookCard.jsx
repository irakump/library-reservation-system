import { useState, useEffect } from "react";
import { getQueueLength } from "../../api/reservationsApi.js";
import Button from "../buttons/Button.jsx";
import FavoriteButton from "../buttons/FavoriteButton.jsx";
import BookButtons from "./BookButtons.jsx";

const BookCard = ({ book, pageType, setOpen, addToLoans }) => {
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
    <div
      className="bg-white rounded-lg p-2 gap-6 shadow hover:shadow-lg hover:opacity-90 transition-all flex max-[350px]:flex-col"
      onClick={() => setOpen(book)}>
      <div className="w-24 max-w-full h-auto mt-1.5 ml-1.5 shrink-0">
        <img
          src={`/books/${pageType === "reservation" ? book.bookIsbn : book.isbn}.jpg`}
          alt={`Book image for ${book.title}`}
          className="w-24 h-auto rounded-sm outline-1 outline-gray-200"
        />
      </div>
      <div className="flex-1">
        <div className="flex justify-between items-start">
          <h3 className="font-bold text-lg">{book.title}</h3>
          <div className="pl-2 pr-1 text-2xl cursor-pointer hover:text-red-700">
            <FavoriteButton book={book} />
          </div>
        </div>

        <p className="text-sm mb-1 text-left">
          {book.authors
            ?.map((author) => `${author.firstName} ${author.lastName}`)
            .join(", ")}
        </p>
        <p className="text-sm mb-1 text-left ">{book.year}</p>
        <p className="text-sm mb-1 text-left capitalize">{book.genre}</p>

        {book.availability === false && queueLength !== null && (
          <p className="text-sm mb-1">Queue length: {queueLength}</p>
        )}

        <div className="mt-auto">
          <BookButtons pageType={pageType} book={book} />
        </div>
      </div>
    </div>
  );
};

export default BookCard;
