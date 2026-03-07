import {useLoanContext} from "../../contexts/LoanContext.jsx";
import {splitDate} from "../../utils/splitDate.js";

function LoanButton({pageType, book, children}) {
    const {addToLoans, removeLoans} = useLoanContext()

    //new loan
    return pageType === "favourite" ? (
        <>
            <p className="text-sm mb-1 text-left">🟢 Available</p>
            <button className="bg-filter font-semibold rounded-xl px-6 py-2 max-[200px]:px-3 max-[200px]:py-1.5 hover:bg-sky-500 float-right cursor-pointer"
                    onClick={e => {
                        e.stopPropagation();
                        addToLoans(book.isbn);
                    }}> {children}
            </button>
        </>
        ) : //return loan
        (
        <>
            <p className="text-sm mb-2 text-left">Due date:  {splitDate(book.dueDate)}</p>
            <button className="bg-filter font-semibold rounded-xl px-6 py-2 max-[200px]:px-2 max-[200px]:py-1.5 hover:bg-sky-500 float-right cursor-pointer"
                    onClick={e => {
                        e.stopPropagation();
                        removeLoans(book.userId, book.isbn, book.loanId);
                    }}>
                {children}
            </button>
        </>
        )
}
export default LoanButton;