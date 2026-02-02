import BookDataPage from "./BookDataPage";
const LoansPage = () => {
  const loansData = [
    {
      id: 1,
      title: "Book Title",
      author: "Author name",
      year: "2025",
      dueDate: "1.1.2025",
    },
    {
      id: 1,
      title: "Book Title",
      author: "Author name",
      year: "2025",
      dueDate: "1.1.2025",
    },
  ];

  return (
    <BookDataPage title="My loans (4)" books={loansData} pageType="loans" />
  );
};

export default LoansPage;
