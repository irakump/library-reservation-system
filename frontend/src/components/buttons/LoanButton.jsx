import { useTranslation } from "react-i18next";
import {useLoanContext} from "../../contexts/LoanContext.jsx";
import {useLayoutDirection} from "../../contexts/LayoutDirectionContext.jsx";

function LoanButton({pageType, book, children}) {
    const {addToLoans, removeLoans} = useLoanContext()
    const { t } = useTranslation("book_card");
    const {formatDate} = useLayoutDirection();

    //new loan
    return pageType === "favourite" ? (
        <>
            <p className="text-sm mb-1 text-left rtl:text-right">{t("available")}</p>
            <button className="bg-actionButton font-semibold rounded-xl px-6 py-2 max-[200px]:px-3 max-[200px]:py-1.5 hover:bg-actionButtonHover float-right rtl:float-left cursor-pointer"
                    onClick={e => {
                        e.stopPropagation();
                        addToLoans(book.isbn);
                    }}> {children}
            </button>
        </>
        ) : //return loan
        (
        <>
            <p className="text-sm mb-2 text-left rtl:text-right">{t("due_date", { date: formatDate(book.dueDate)})}</p>
            <button className="bg-actionButton font-semibold rounded-xl px-6 py-2 max-[200px]:px-2 max-[200px]:py-1.5 hover:bg-actionButtonHover float-right rtl:float-left cursor-pointer"
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