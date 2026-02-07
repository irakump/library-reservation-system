import BookCard from "../components/Books/BookCard.jsx";
import { useState } from 'react';
import BookModal from "../components/Books/BookModal.jsx";


const BookDataPage = ({ title, books, pageType, action }) => {
  const [open, setOpen] = useState(null);  //passes bookobject

  return (
    <>
      <div className="min-h-screen">
        <h1 className="text-2xl font-bold text-center text-heading p-7">
          {title}
        </h1>
        <div className='grid grid-cols-1 lg:grid-cols-2 md:mx-5 lg:mx-15 xl:mx-30  bg-profileBackground p-15 gap-4'>
          {books.map((book) => (
            <BookCard 
            key={book.id} 
            book={book} 
            pageType={pageType}
            setOpen={setOpen}
            />
          ))}
        </div>
        {open && (
      <BookModal
      book={open}
      pageType={pageType}
      setOpen={setOpen}
      >

      </BookModal>
      )}
      </div>
    </>
  );
};
export default BookDataPage;
