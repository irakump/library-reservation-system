import BookDataPage from "./BookDataPage";

//This function can be modified for later use*
function CancelReservation(book) {
  console.log(`Canceled recervation for: ${book.title}`);
  };


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


  //action object includes functionality and text for buttons (return, loan, cancel jne). Works like prop
  const action = {
    BtnText: "Cancel",
    return: (book) => CancelReservation(book),
    p: (book) => `🔴 Not available`
  };
  

  return (
    <BookDataPage
      title="My Reservations (4)"
      books={reservationsData}
      pageType="reservation"
      action={action}
    />
  );
};

export default ReservationPage;
