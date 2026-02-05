import { useState } from "react";

const BookCard = ({ book, pageType }) => {
  const [isFavourite, setIsFavourite] = useState(false);

  return (
    <div className="bg-white rounded-lg shadow-sm p-2 flex gap-6">
      <div className="w-24 h-32 mt-1.5 ml-1.5 bg-gray-300 rounded shrink-0 "></div>

      <div className="flex-1">
        <div className="flex justify-between items-start">
          <h3 className="font-bold text-lg">{book.title}</h3>
          <button
            className="text-xl"
            onClick={() => setIsFavourite(!isFavourite)}
          >
            {isFavourite ? "♥︎" : "♡"}
          </button>
        </div>

        <p className="text-sm mb-1 text-left">{book.author}</p>
        <p className="text-sm mb-1 text-left">Year: {book.year}</p>

        {pageType === "loans" && (
          <>
            <p className="text-sm mb-1 text-left">Due date: {book.dueDate}</p>

            <button className="bg-filter font-semibold rounded-lg px-4 py-2 float-right">
              Return
            </button>
          </>
        )}
        {pageType === "reservation" && (
          <>
            <p className="text-sm mb-1 text-left">🟢 Available</p>

            <button className="bg-filter font-semibold rounded-lg px-4 py-2 float-right">
              Cancel
            </button>
          </>
        )}
        {pageType === "favourite" && (
          <>
            <p className="text-sm mb-1 text-left">🟢 Available</p>

            <button className="bg-filter font-semibold rounded-xl px-6 py-2 float-right">
              Loan
            </button>
          </>
        )}

        {pageType === "history" && (
          <>
            <p className="text-sm mb-1 text-left">
              Borrowed: {book.borrowedDate}
            </p>
            <p className="text-sm mb-1 text-left">
              Returned: {book.returnedDate}
            </p>
          </>
        )}
      </div>
    </div>
  );
};

export default BookCard;
