import { useState, useEffect } from "react";
import { getQueueLength } from "../../api/reservationsApi.js";
import FavoriteButton from "../buttons/FavoriteButton.jsx";
import BookButtons from "./BookButtons.jsx";
import { useAuth } from "../../contexts/AuthContext.jsx";
import { useTranslation } from "react-i18next";

const BookCard = ({ book, pageType, setOpen, addToLoans }) => {
  const [queueLength, setQueueLength] = useState(null);
  const isbn = pageType === "reservation" ? book.bookIsbn : book.isbn;
  const { user, isLoggedIn } = useAuth();
  const { t } = useTranslation("book_card");

  useEffect(() => {
    if (book.availability === false) {
      getQueueLength(isbn)
        .then((res) => setQueueLength(res.data.queueLength))
        .catch(() => setQueueLength(null));
    }
  }, [isbn, book.availability]);

  return (
    <div
      className="bg-white rounded-lg p-2 gap-6 shadow hover:shadow-lg hover:opacity-90 hover:scale-[1.02] hover:cursor-pointer transition-all flex max-[350px]:flex-col min-w-[120px]"
      onClick={() => setOpen(book)}
    >
      <div className="w-24 max-w-full h-auto mt-1.5 ml-1.5 shrink-0 pr-2 sm:pr-0">
        <img
          src={`/books/${pageType === "reservation" ? book.bookIsbn : book.isbn}.jpg`}
          alt={`Book image for ${book.title}`}
          className="w-24 min-w-16 h-auto rounded-sm outline-1 outline-gray-200"
        />
      </div>
      <div className="flex-1 flex flex-col">
        <div className="flex max-[270px]:flex-col-reverse justify-between items-start min-w-0">
          <h3 className="font-bold max-[250px]:text-base max-[200px]:text-sm text-lg flex-1 min-w-0">
            {book.title}
          </h3>
          <div className="sm:pl-2 sm:pr-1 text-2xl">
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

        {book.availability === false && user && isLoggedIn && (
          <p className="text-sm mb-1">{t("queue_length", { queue_length: queueLength ?? "..." })}</p>
        )}

        <div className="mt-auto">
          <BookButtons pageType={pageType} book={book} />
        </div>
      </div>
    </div>
  );
};

export default BookCard;
