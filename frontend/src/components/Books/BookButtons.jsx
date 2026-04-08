import LoanButton from "../buttons/LoanButton.jsx";
import ReserveButton from "../buttons/ReserveButton.jsx";
import HistoryText from "../buttons/HistoryText.jsx";
import { useTranslation } from "react-i18next";
import {useAuth} from "../../contexts/AuthContext.jsx";
import {useLoanContext} from "../../contexts/LoanContext.jsx";

function BookButtons({pageType, book}) {
    const { t } = useTranslation("button")
    const {isLoggedIn} = useAuth()
    const {loans} = useLoanContext()
    let returnButton;
    let loan;

    if (book && isLoggedIn && loans.length > 0) {
        loan = loans.find(b => b.isbn === book.isbn) || null;
    }


    switch (pageType) {
        case "home":
            if (book.availability) {
                returnButton = <LoanButton pageType={pageType} book={book}>{t("loan")}</LoanButton>
                break;
            }
            else if (loans.find((b) => b.isbn === book.isbn)) {
                returnButton = <LoanButton pageType={pageType} book={loan}>{t("return")}</LoanButton>
                break;
            }
            else {
                returnButton = <ReserveButton pageType={pageType} book={book}>{t("reserve")}</ReserveButton>
                break;
            }

        case "loans":
            returnButton = <LoanButton pageType={pageType} book={book}>{t("return")} </LoanButton>
            break;

        case "reservation":
            returnButton = <ReserveButton pageType={pageType} book={book}> {t("cancel")}</ReserveButton>
            break;

        case "history":
            returnButton = <HistoryText book={book}></HistoryText>
            break;

        default:
            returnButton = null;
    }

    return returnButton;

}
export default BookButtons;