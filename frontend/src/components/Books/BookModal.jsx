import Button from "../buttons/Button.jsx";
import FavoriteButton from "../buttons/FavoriteButton.jsx";
import BookButtons from "./BookButtons.jsx";
import { getQueueLength } from "../../api/reservationsApi.js";
import { useEffect, useState } from "react";
import { useAuth } from "../../contexts/AuthContext.jsx";
import { useTranslation } from "react-i18next";

const BookModal = ({ book, pageType, setOpen }) => {
  const { t } = useTranslation("book_card");
  const [queueLength, setQueueLength] = useState(null);
  //const isbn = book.isbn;
  const { user, isLoggedIn } = useAuth();

  console.log(book);
  useEffect(() => {
    if (book.availability === false) {
      getQueueLength(book.isbn)
        .then((res) => setQueueLength(res.data.queueLength))
        .catch(() => setQueueLength(null));
    }
  }, [book]);

    if (!book) return;

  return (
    <>
      <div
        className="fixed inset-0 flex items-start md:items-center justify-center bg-black/40 px-2 pt-2"
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
            <div className="absolute cursor-pointer hover:text-red-700 max-sm:pt-2 sm:top-10 ltr:right-4 rtl:left-4 text-3xl">
              <FavoriteButton book={book}></FavoriteButton>
            </div>

            <div className="flex flex-col gap-4">
              <div className="flex flex-col-reverse sm:flex-row">
                <div className="w-36 sm:w-24 max-w-full mt-3 sm:mt-1 mr-0.5 shrink-0 max-sm:self-start max-sm:ml-0">
                  <img
                    src={`/books/${book.isbn}.jpg`}
                    alt={`Book image for ${book.title}`}
                    className="w-36 min-w-20 sm:w-24 h-auto rounded-sm outline-1 outline-gray-200"
                  />
                </div>
                <div className="max-sm:pt-2 min-w-0">
                  <div className="max-sm:self-center">
                    <h1 className="font-bold text-lg text-left rtl:text-right p-3 max-sm:pl-0 pt-0 ltr:pr-6 ltr:sm:pr-2 sm:ml-2">
                      {book.title}
                    </h1>
                  </div>

                  <div className="pl-0 sm:pl-5">
                    <p className="text-sm mb-1">
                      {book.authors
                        ?.map(
                          (author) => `${author.firstName} ${author.lastName}`,
                        )
                        .join(", ")}
                    </p>
                    <p className="text-sm mb-1">{book.year} </p>
                  </div>
                </div>
              </div>

              <div className="flex-1">
                <p className="text-sm mb-1 max-sm:ml-0">{book.description}</p>
              </div>
            </div>
            <div className="mt-3 flex flex-col sm:flex-row items-start sm:items-center gap-2 capitalize">
              <span className="px-3 py-1 text-xs rounded-full bg-gray-100">
                {book.language}
              </span>
              <span className="px-3 py-1 text-xs rounded-full bg-gray-100">
                {book.genre}
              </span>
            </div>
            <div className="mt-6 flex flex-col max-sm:gap-2">
              <div className="text-sm items-start">
                {book.availability === false && user && isLoggedIn &&  (
                  <p>{t("queue_length")}: {queueLength ?? "..."}</p>
                )}
              </div>
              <div className="flex flex-col items-start sm:flex-row sm:items-end justify-between max-sm:gap-2">
                {/* Button for loan/reserve/return/history data on history page */}
                <BookButtons pageType={pageType} book={book}></BookButtons>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
};
export default BookModal;
