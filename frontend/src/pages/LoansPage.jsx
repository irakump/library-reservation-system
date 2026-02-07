import BookDataPage from "./BookDataPage";

const LoansPage = () => {
  let loansData = [
    {
      id: 1,
      title: "Kirja1",
      author: "Author name",
      year: "2025",
      dueDate: "1.1.2025",
    },
    {
      id: 2,
      title: "Kirja2",
      author: "Author name",
      year: "2025",
      dueDate: "1.1.2025",
    },
    {
      id: 3,
      title: "Book Title",
      author: "Author name",
      year: "2025",
      dueDate: "1.1.2025",
    },
    {
      id: 4,
      title: "Book Title",
      author: "Author name",
      year: "2025",
      dueDate: "1.1.2025",
    },
  ];


  return (
    <BookDataPage 
    title="My loans (4)" 
    books={loansData} 
    pageType="loans" 
    />
  );
};

export default LoansPage;
