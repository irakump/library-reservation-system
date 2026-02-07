import BookDataPage from "./BookDataPage";
import { getAvailibility } from "../utils/getAvailibility";

//This function is just for demostration and it is NOT meant for loaning and is at the wrong place too!
function LoanBook(book) {
  console.log(`${book.title} loaned`);
};

const FavouritePage = () => {
  const favouriteData = [
    {
      id: 1,
      title: "Book Title",
      author: "Author name",
      year: "2025",
      available: false,
    },
    {
      id: 2,
      title: "Book Title",
      author: "Author name",
      year: "2025",
      available: false,
    },
    {
      id: 3,
      title: "Book Title",
      author: "Author name",
      year: "2025",
      available: true,
    },
    {
      id: 4,
      title: "Book Title",
      author: "Author name",
      year: "2025",
      available: true,
    },
  ];


   //action object includes paragraph, functionality and text for buttons (return, loan, cancel jne). works like prop. This is just for demostrating, not sure if best option for this
   const action = {
    BtnText: "Loan",
    func: (book) => LoanBook(book),
    p: () => ``
  };

  return (
    <BookDataPage
      title="My Favourites (4)"
      books={favouriteData}
      pageType="favourite"
      action={action}
    />
  );
};

export default FavouritePage;
