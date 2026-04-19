import { useState, useEffect } from "react";
import { getQueueLength } from "../../api/reservationsApi.js";
import FavoriteButton from "../buttons/FavoriteButton.jsx";
import BookButtons from "./BookButtons.jsx";
import { useAuth } from "../../contexts/AuthContext.jsx";
import { useTranslation } from "react-i18next";
import PropTypes from "prop-types";
import {localizeYear} from "../../utils/utils.js";

const BookCard = ({ book, pageType, setOpen}) => {
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


  const localizedYear = localizeYear(book.year);


  return (
    <div
      className="bg-white rounded-lg p-2 gap-6 shadow hover:shadow-lg hover:opacity-90 hover:scale-[1.02] hover:cursor-pointer transition-all flex max-[350px]:flex-col min-w-[120px]"
      onClick={() => setOpen(book)}
    >
      <div className="w-24 max-w-full h-auto mt-1.5 ltr:ml-1.5 shrink-0 pr-2 rtl:mr-1.5 sm:pr-0">
        <img
          src={`/books/${book.isbn}.jpg`}
          alt={`Book: ${book.title}`}
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

        <p className="text-sm mb-1 text-left rtl:text-right">
          {book.authors
            ?.map((author) => `${author.firstName} ${author.lastName}`)
            .join(", ")}
        </p>
        <p className="text-sm mb-1 text-left rtl:text-right">{localizedYear}</p>
        <p className="text-sm mb-1 text-left rtl:text-right capitalize">{book.genre}</p>

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

BookCard.propTypes = {
  book: PropTypes.shape({
    isbn: PropTypes.string,
    bookIsbn: PropTypes.string,
    title: PropTypes.string,
    year: PropTypes.oneOfType([PropTypes.string, PropTypes.number]),
    genre: PropTypes.string,
    authors: PropTypes.arrayOf(
        PropTypes.shape({
          firstName: PropTypes.string,
          lastName: PropTypes.string,
        })
    ),
    availability: PropTypes.bool,
  }).isRequired,

  pageType: PropTypes.string.isRequired,
  setOpen: PropTypes.func.isRequired,
};

export default BookCard;
