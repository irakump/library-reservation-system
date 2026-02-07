

export function getAvailibility(book) {
  if (book.available) {
    return {
      BtnText: "Loan",
      onClick: (book) => loanBook(book),
    };
  } else {
    return {
      BtnText: "Reserve",
      onClick: () => reserveBook(book),
    };

  }
} 
