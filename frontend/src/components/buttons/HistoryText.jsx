//not a button but goes to BookButtons
import { useTranslation } from "react-i18next";
import {splitDate} from "../../utils/splitDate.js";

function HistoryText({book}) {
    const { t } = useTranslation("book_card")

    return (
          <>
            <p className="text-sm mb-1 text-left">
              {t("history_borrowed", { date: splitDate(book.createdAt)})}
            </p>
            <p className="text-sm mb-1 text-left">
              {t("history_returned", { date: splitDate(book.returnDate)})}
            </p>
          </>
    )
}
export default HistoryText;