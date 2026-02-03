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
  return (
    <BookDataPage title="My History" books={historyData} pageType="history" />
  );
};

export default HistoryPage;
