import LoanButton from "../buttons/LoanButton.jsx";
import ReserveButton from "../buttons/ReserveButton.jsx";
import HistoryText from "../buttons/HistoryText.jsx";
import { useTranslation } from "react-i18next";

function BookButtons({pageType, book}) {
    const { t } = useTranslation("button")
    let returnButton;

    switch (pageType) {
        case "favourite":
            if (book.availability) {
                returnButton = <LoanButton pageType={pageType} book={book}>{t("loan")}</LoanButton>
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