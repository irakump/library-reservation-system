import { useState } from "react";
import FavBtn from "./FavBtn";

function Button({ onClick, children }) {
  return (
    <button className="bg-filter font-semibold rounded-xl px-6 py-2 hover:bg-sky-500 float-right"
    onClick={e => {
      e.stopPropagation();
      onClick();
      ;
    }}>
      {children}
    </button>
  );
}





const BookCard = ({ book, pageType, setOpen }) => {
  

  return (
    <div className="bg-white rounded-lg shadow-lg p-2 flex gap-6" onClick={() => setOpen(book.id)}>
      <div className="w-24 h-32 mt-1.5 ml-1.5 bg-gray-300 rounded shrink-0" ></div>

      <div className="flex-1">
        <div className="flex justify-between items-start">
          <h3 className="font-bold text-lg">{book.title}</h3>
          <FavBtn 
          className="text-xl"
          onClick={() => setIsFavourite(!isFavourite)
          }>
        </FavBtn> 
        </div>

        <p className="text-sm mb-1 text-left">{book.author}</p>
        <p className="text-sm mb-1 text-left">Year: {book.year}</p>

        {pageType === "loans" && (
          <>
            <p className="text-sm mb-1 text-left">Due date: {book.dueDate}</p>

            <button className="bg-filter font-semibold rounded-lg px-4 py-2 hover:bg-sky-500 float-right">
              Return
            </button>
          </>
        )}
        {pageType === "reservation" && (
          <>
            <p className="text-sm mb-1 text-left">🟢 Available</p>

            <button className="bg-filter font-semibold rounded-lg px-4 py-2 hover:bg-sky-500 float-right">
              Cancel
            </button>
          </>
        )}
        {pageType === "favourite" && (
          <>
            <p className="text-sm mb-1 text-left">🟢 Available</p>

            <Button>
              Loan
            </Button>
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
