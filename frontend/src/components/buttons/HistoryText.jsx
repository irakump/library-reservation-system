//not a button but goes to BookButtons
import { useTranslation } from "react-i18next";
import {useLayoutDirection} from "../../contexts/LayoutDirectionContext.jsx";
import PropTypes from "prop-types";

function HistoryText({book}) {
    const {formatDate} = useLayoutDirection();
    const { t } = useTranslation("book_card")

    return (
          <>
            <p className="text-sm mb-1 text-left rtl:text-right">
              {t("history_borrowed", { date: formatDate(book.createdAt)})}
            </p>
            <p className="text-sm mb-1 text-left rtl:text-right">
              {t("history_returned", { date: formatDate(book.returnDate)})}
            </p>
          </>
    )
}

HistoryText.propTypes = {
    book: PropTypes.shape({
        createdAt: PropTypes.string,
        returnDate: PropTypes.string,
    }).isRequired,
};

export default HistoryText;