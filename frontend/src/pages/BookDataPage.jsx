import Footer from "../components/footer/Footer";
import BookCard from "../components/Books/BookCard.jsx";
import { useState } from 'react';
import BookModal from "../components/home/BookModal.jsx";

const BookDataPage = ({ title, books, pageType }) => {
  const [open, setOpen] = useState(null);

  return (
    <>
      <div className="bg-background min-h-screen">
        <h1 className="text-2xl font-bold text-center text-heading p-7">
          {title}
        </h1>
        <div className="mx-auto p-4 space-y-4 bg-profileBackground">
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
      open={open}
      setOpen={setOpen}
      />
      )}
      </div>
    </>
  );
};
export default BookDataPage;
