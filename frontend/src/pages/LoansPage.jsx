import BookDataPage from "./BookDataPage";


//This function can be modified for later use
function ReturnBook(book) {
  console.log(`${book.title} returned`);
};


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



 //action object includes functionality and text for buttons (return, loan, cancel jne). works like prop. check others from other pages
  const action = {
    BtnText: "Return",
    func: (book) => ReturnBook(book),
    p: (book) => `Due date: ${book.dueDate}`
  };


  return (
    <BookDataPage 
    title="My loans (4)" 
    books={loansData} 
    pageType="loans" 
    action={action}
    />
  );
};

export default LoansPage;
