import { useState } from "react";
import Button from "./Button";
import { getPage } from "../../utils/getPage";



const BookCard = ({ book, pageType, setOpen, }) => {
  const [isFavourite, setIsFavourite] = useState(false); //will be replaced
  const page = getPage(pageType, book)

  return (
    <div className="bg-white rounded-lg p-2 flex gap-6 shadow hover:shadow-lg hover:opacity-90 transition-all " onClick={() => setOpen(book)}>
      <div className="w-24 h-32 mt-1.5 ml-1.5 bg-gray-300 rounded shrink-0" ></div>

      <div className="flex-1">
        <div className="flex justify-between items-start">
          <h3 className="font-bold text-lg">{book.title}</h3>
          <button 
          className="text-2xl cursor-pointer hover:text-red-700"
          onClick={(e) => {
            e.stopPropagation();
            setIsFavourite(!isFavourite);
          }}> {isFavourite ? "♥︎" : "♡"}
        </button> 
        </div>

        <p className="text-sm mb-1 text-left">{book.author}</p>
        <p className="text-sm mb-1 text-left ">{book.year}</p>
        <p className="text-sm mb-1 text-left capitalize">{book.genre}</p>

        {page.showDates && (
          <>
            <p className="text-sm mb-1 text-left">Borrowed: {book.borrowedDate}</p>
            <p className="text-sm mb-1 text-left">Returned: {book.returnedDate}</p>
          </>
        )}

        <p className="text-sm mb-0 text-left">{page.p}</p>
        

        {/* Button for loan/reserve/return jene */}
        {!page.showDates && (
          <Button onClick={()=> page.action(book)}> {page.BtnText} </Button>
        )}
    
      

      </div>
    </div>
  );
};

export default BookCard;
