import { useState } from "react"

const BookInfo = ({book, setOpen}) => {
    const [fav, setfav] = useState(false);

    return (
      <div className="w-full mx-auto">
        <div className="flex gap-4 sm:gap-6 rounded-2xl border border-blue-200 bg-white p-4 sm:p-6 shadow-sm" onClick={() => setOpen(book.id)}>
          <div className="h-32 w-24 sm:h-40 sm:w-28 flex-shrink-0 overflow-hidden rounded-lg bg-gray-200">
            <img src="https://placehold.co/100x150" alt='placeholder' className="h-full w-full object-cover"/>
          </div>
          <div className="flex flex-1 flex-col">
            <div className="flex items-start justify-between">
              <div className="text-gray-700 capitalize">
                <h3 className="text-lg font-bold"> {book.name} </h3>
                <p className="text-gray-700"> {book.author}</p>
                <p className="text-gray-700">{book.year}</p>
              </div>
              <button className="text-gray-700 hover:text-red-600">
              <div className="h-6 w-6 text-3xl xl:text-4xl" >♡</div>
              </button>
            </div>
            <div className="mt-auto flex items-center justify-between pt-4">
              <div className="flex items-center gap-2">
  
              <span className="text-sm sm:text-base">🔴Not available</span>
              </div>
              <button className="rounded-xl bg-filter px-6 py-2 text-sm sm:text-base font-semibold text-black hover:bg-sky-500   transition">Loan</button>
            </div>
          </div>
        </div>
      </div>
    );

}
export default BookInfo