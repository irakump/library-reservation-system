import BookCard from "../components/Books/BookCard.jsx";
import { useState } from 'react';
import BookModal from "../components/Books/BookModal.jsx";

const BookDataPage = ({ title, books, pageType }) => {
  const [open, setOpen] = useState(null);

  return (
    <>
      <div className="bg-background min-h-screen">
        <h1 className="text-2xl font-bold text-center text-heading p-7">
          {title}
        </h1>
        <div className='grid grid-cols-1 lg:grid-cols-2 md:mx-5 lg:mx-15 xl:mx-30 md:grid-rows-7 bg-profileBackground p-15 gap-4'>
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
      setOpen={setOpen}
      />
      )}
      </div>
    </>
  );
};
export default BookDataPage;
