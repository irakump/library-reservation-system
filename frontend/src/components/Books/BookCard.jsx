import { useState } from "react";


function Button({ onClick, children }) {
  return (
    <button className="bg-filter font-semibold rounded-xl px-6 py-2 hover:bg-sky-500 float-right cursor-pointer"
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
  const [isFavourite, setIsFavourite] = useState(false);

  return (
    <div className="bg-white rounded-lg p-2 flex gap-6 shadow hover:shadow-xl hover:opacity-90 transition-all " onClick={() => setOpen(book)}>
      <div className="w-24 h-32 mt-1.5 ml-1.5 bg-gray-300 rounded shrink-0" ></div>

      <div className="flex-1">
        <div className="flex justify-between items-start">
          <h3 className="font-bold text-lg">{book.title}</h3>
          <button 
          className="text-xl cursor-pointer"
          onClick={(e) => {
            e.stopPropagation();
            setIsFavourite(!isFavourite);
          }}> {isFavourite ? "♥︎" : "♡"}
        </button> 
        </div>

        <p className="text-sm mb-1 text-left">{book.author}</p>
        <p className="text-sm mb-1 text-left">Year: {book.year}</p>

        {pageType === "loans" && (
          <>
            <p className="text-sm mb-1 text-left">Due date: {book.dueDate}</p>

            <Button>
              Return
            </Button>
          </>
        )}
        {pageType === "reservation" && (
          <>
            <p className="text-sm mb-1 text-left">🟢 Available</p>

            <Button>
              Cancel
            </Button>
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
