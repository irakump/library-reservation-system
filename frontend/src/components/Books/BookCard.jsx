const BookCard = ({ book, pageType }) => {
  return (
    <div>
      <h3>{book.title}</h3>
      <button>♡</button>
      <p>{book.author}</p>
      <p>{book.year}</p>

      {pageType === "loans" && (
        <>
          <p>Due date: {book.dueDate}</p>
          <button>Renew Loan</button>
        </>
      )}
    </div>
  );
};

export default BookCard;
