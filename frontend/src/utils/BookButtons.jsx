import LoanButton from "../components/buttons/LoanButton.jsx";
import ReserveButton from "../components/buttons/ReserveButton.jsx";
import HistoryText from "../components/buttons/HistoryText.jsx";

function BookButtons({pageType, book}) {
    let returnButton;

    switch (pageType) {
        case "favourite":
            if (book.availability) {
                returnButton = <LoanButton pageType={pageType} book={book}> Loan </LoanButton>
                break;
            }
            else {
                returnButton = <ReserveButton pageType={pageType} book={book}> Reserve </ReserveButton>
                break;
            }
        case "loans":
            returnButton = <LoanButton pageType={pageType} book={book}> Return </LoanButton>
            break;

        case "reservation":
            returnButton = <ReserveButton pageType={pageType} book={book}> Cancel </ReserveButton>
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