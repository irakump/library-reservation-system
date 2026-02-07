//functions for buttons

export function ReserveBook(book) {
    console.log(`${book.title} reserved`);
};

export function ReturnBook(book) {
  console.log(`${book.title} returned`);
};

export function LoanBook(book) {
  console.log(`${book.title} loaned`);
};

export function CancelReservation(book) {
  console.log(`Canceled recervation for: ${book.title}`);
  };


export function getPage(pageType, book) {
    switch (pageType) {
        case "favourite":
            if (book.available) {
                return { 
                    BtnText: "Loan", 
                    action: (book) => LoanBook(book), p: "🟢 Available"}
            } 
            else return { 
                BtnText: "Reserve", 
                action: (book) => ReserveBook(book), p: "🔴 Not available"}

        case "loans":
        return { 
            BtnText: "Return", 
            action: (book) => ReturnBook(book)}

        case "reservation":
        return { 
            BtnText: "Cancel", 
            action: (book) => CancelReservation(book) }

        case "history":
        return { BtnText: null, showDates: true }

        default:
        return {};

        }
}