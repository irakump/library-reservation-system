export function getAvailibility(book) {
  if (book.available) {
    return {
      BtnText: "Buy",
      onClick: () => buyBook(book),
    };
  } else {
    return {
      text: "Reserve",
      onClick: () => reserveBook(book),
    };

  }
} 
