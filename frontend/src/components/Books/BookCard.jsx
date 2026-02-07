import { useState } from "react";
import Button from "./Button";


//maybe use conditional rendering just here or bookdatapage to see if book is available or no

//possibly not needed
export function getAvailibility(book) {
  if (book.available) {
    return {
      BtnText: "Loan",
      onClick: (book) => loanBook(book),
    };
  } else {
    return {
      BtnText: "Reserve",
      onClick: () => reserveBook(book),
    };

  }
} 


const BookCard = ({ book, pageType, setOpen, action, available }) => {
  const [isFavourite, setIsFavourite] = useState(false); //will be replaced

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
        <p className="text-sm mb-1 text-left">{action.p(book)}</p>


        {pageType !== "history" || pageType !== "favourite" && 
        <Button onClick={()=> action.func(book)}> {action.BtnText} </Button>
        } 


        {pageType === "favourite" && 
        <Button onClick={() => available.onClick}> {available.BtnText}
        </Button> }


      </div>
    </div>
  );
};

export default BookCard;
