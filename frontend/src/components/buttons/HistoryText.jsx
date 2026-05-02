//not a button but goes to BookButtons
import { useTranslation } from "react-i18next";
import PropTypes from "prop-types";
import {localizeDate} from "../../utils/DateUtils.js";

function HistoryText({book}) {
    const { t } = useTranslation("book_card")
    const createdDate = localizeDate(book.createdAt);
    const returnDate = localizeDate(book.returnDate);

    return (
          <>
            <p className="text-sm mb-1 text-left rtl:text-right">
              {t("history_borrowed", { date: createdDate})}
            </p>
            <p className="text-sm mb-1 text-left rtl:text-right">
              {t("history_returned", { date: returnDate})}
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