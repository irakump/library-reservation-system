import NavBar from "../components/navbar/NavBar.jsx";
import { MenuProvider } from "../contexts/MenuContext.jsx";
import BookCard from "../components/books/BookCard.jsx";

const BookDataPage = ({ title, books, pageType }) => {
  return (
    <>
      <MenuProvider>
        <NavBar />
      </MenuProvider>
      <div className="bg-background min-h-screen">
        <h1 className="text-2xl font-bold text-center text-heading p-7">
          {title}
        </h1>
        <div className="mx-auto p-4 space-y-4 bg-profileBackground">
          {books.map((book) => (
            <BookCard key={book.id} book={book} pageType={pageType} />
          ))}
        </div>
      </div>
    </>
  );
};
export default BookDataPage;
