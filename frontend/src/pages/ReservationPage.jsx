import BookDataPage from "./BookDataPage";

const ReservationPage = () => {
  const reservationsData = [
    {
      id: 1,
      title: "Book1",
      author: "Author name",
      year: "2025",
    },
    {
      id: 2,
      title: "Book2",
      author: "Author name",
      year: "2025",
    },
    {
      id: 3,
      title: "Book3",
      author: "Author name",
      year: "2025",
    },
    {
      id: 4,
      title: "Book4",
      author: "Author name",
      year: "2025",
    },
  ];


  return (
    <BookDataPage
      title="My Reservations (4)"
      books={reservationsData}
      pageType="reservation"
    />
  );
};

export default ReservationPage;
