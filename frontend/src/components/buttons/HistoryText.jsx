//not a button but goes to BookButtons

function HistoryText({book}) {
    return (
          <>
            <p className="text-sm mb-1 text-left">
              Borrowed: {book.borrowedDate}
            </p>
            <p className="text-sm mb-1 text-left">
              Returned: {book.returnedDate}
            </p>
          </>
    )
}
export default HistoryText;