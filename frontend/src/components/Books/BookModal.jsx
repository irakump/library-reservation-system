import Button from "./Button"
import { useState } from "react";
import { getPage } from "../../utils/getPage";

const BookModal = ({book, pageType, setOpen}) => {

    const [isFavourite, setIsFavourite] = useState(false); 
    const page = getPage(pageType, book)


    return(
        <>
        <div className="fixed inset-0 flex items-center justify-center bg-black/40" onClick={() => setOpen(null)}>
          <div className="relative w-full max-w-md bg-white rounded-xl p-9 border-20 border-filter" 
          onClick={(e) => e.stopPropagation()}>
            <button 
            onClick={() => setOpen(null)} className="absolute top-0 right-4 text-xl">✕</button>
            <div >
              <button 
                className="text-xl cursor-pointer absolute top-10 right-4 text-4xl"
                onClick={(e) => {
                e.stopPropagation();
                setIsFavourite(!isFavourite);
                }}> 
                {isFavourite ? "♥︎" : "♡"}
                </button> 

              <div className="flex gap-4">
              <img src="https://placehold.co/100x150" alt="Book cover" className="w-24 h-36 object-cover"
                />
              <div className="flex-1">
                  <h1 className="font-bold text-lg">{book.title}</h1>
                  <p className="text-sm mb-1"> {book.author} </p>
                  <p className="text-sm mb-1">{book.year} </p>
                  <p className="text-sm mb-1">
                    Description
                  </p>
                </div>
                </div>
                <div className="mt-6 text-sm text-gray-700 space-y-1">
                  <p><span className="font-medium">Language:</span> Finnish</p>
                  <p><span className="font-medium">Genre:</span> History</p>
                </div>
                <div className="mt-3 flex items-center gap-2">
                  <span className="px-3 py-1 text-xs rounded-full bg-gray-100">Finnish</span>
                  <span className="px-3 py-1 text-xs rounded-full bg-gray-100">History</span>
                
                </div>
                <div className="mt-6 flex items-end justify-between">
                  <div className="text-sm">
                    <p className="flex items-center gap-2"></p>


                    {/*for history page */}
                    {page.showDates && (
                      <>
                        <p className="text-sm mb-1 text-left">Borrowed: {book.borrowedDate}</p>
                        <p className="text-sm mb-1 text-left">Returned: {book.returnedDate}</p>
                      </>
                    )}


                    {/*queue*/}
                    {page.BtnText == "Reserve" && (
                      <>
                        <p className="mt-1">2 people in queue</p>
                        <p className="text-gray-500">Estimated loan date x.x.2026</p>
                      </>

                    )}

                    {/* Button for loan/reserve/return jene */}
                  </div>
                  <Button onClick={()=> page.action(book)}> {page.BtnText} </Button>
                 
                </div>
            </div>    
          </div>
        </div>

        </>
    )
}
export default BookModal