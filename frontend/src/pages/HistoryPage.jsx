import { act } from "react";
import BookDataPage from "./BookDataPage";
const HistoryPage = () => {
  const historyData = [
    {
      id: 1,
      title: "Book Title",
      author: "author name",
      year: 2025,
      borrowedDate: "18.11.2024",
      returnedDate: "25.11.2024",
    },
    {
      id: 2,
      title: "Book Title",
      author: "author name",
      year: 2025,
      borrowedDate: "18.11.2024",
      returnedDate: "25.11.2024",
    },
    {
      id: 3,
      title: "Book Title",
      author: "author name",
      year: 2025,
      borrowedDate: "18.11.2024",
      returnedDate: "25.11.2024",
    },
    {
      id: 4,
      title: "Book Title",
      author: "author name",
      year: 2025,
      borrowedDate: "18.11.2024",
      returnedDate: "25.11.2024",
    },
  ];

   //action object includes functionality and text for buttons (return, loan, cancel jne). Works like prop
  const action = {
    p: (book) => `Borrowed: ${book.borrowedDate}`,
    p1: (book) => `Returned: ${book.returnedDate}`,
  };


  return (
    <BookDataPage 
    title="My History" 
    books={historyData} 
    pageType="history" 
    action={action}
    />
  );
};

export default HistoryPage;
