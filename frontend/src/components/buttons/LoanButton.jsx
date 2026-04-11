import { useTranslation } from "react-i18next";
import {useLoanContext} from "../../contexts/LoanContext.jsx";
import {useLayoutDirection} from "../../contexts/LayoutDirectionContext.jsx";
import {useSearchResult} from "../../contexts/SearchResultContext.jsx";
import {useSearchFilters} from "../../contexts/SearchFilterContext.jsx";
import PropTypes from "prop-types";

function LoanButton({pageType, book, children}) {
    const {addToLoans, removeLoans} = useLoanContext()
    const { t } = useTranslation("book_card");
    const {formatDate} = useLayoutDirection();
    const {fetchSearchResults} = useSearchResult();
    const {setSearchFilters} = useSearchFilters();

    //new loan
    return pageType === "home" && book.availability ? (
        <>
            <p className="text-sm mb-1 text-left rtl:text-right">{t("available")}</p>
            <button className="bg-actionButton font-semibold rounded-xl px-6 py-2 max-[200px]:px-3 max-[200px]:py-1.5 hover:bg-actionButtonHover float-right rtl:float-left cursor-pointer"
                    onClick={async (e) => {
                        e.stopPropagation();
                        await addToLoans(book.isbn);
                        await fetchSearchResults(); //fetches search results again after loaning
                    }}> {children}
            </button>
        </>
        ) : //return loan
        (
        <>
            <p className="text-sm mb-2 text-left rtl:text-right text-red-600">{t("due_date", { date: formatDate(book.dueDate)})}</p>
            <button className="bg-actionButton font-semibold rounded-xl px-6 py-2 max-[200px]:px-2 max-[200px]:py-1.5 hover:bg-actionButtonHover float-right rtl:float-left cursor-pointer"
                    onClick={async (e) => {
                        e.stopPropagation();
                        await removeLoans(book.userId, book.isbn, book.loanId);
                        await setSearchFilters([]);
                        await fetchSearchResults()
                    }}>
                {children}
            </button>
        </>
        )
}

LoanButton.propTypes = {
    pageType: PropTypes.string.isRequired,
    children: PropTypes.node.isRequired,
    book: PropTypes.shape({
        isbn: PropTypes.string,
        availability: PropTypes.bool,
        dueDate: PropTypes.string,
        userId: PropTypes.string,
        loanId: PropTypes.string,
    }).isRequired,
};

export default LoanButton;