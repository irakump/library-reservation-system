//functions for buttons

export function ReserveBook(book) {
    console.log(`${book.title} reserved`);
    alert(`Reservation for ${book.title} succeed`);
};

export function ReturnBook(book) {
    confirm(`Returning ${book.title}`);
};

export function LoanBook(book) {
    alert(`Loaning ${book.title}`);
};

export function CancelReservation(book) {
    confirm(`Canceling recervation for: ${book.title}`);
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