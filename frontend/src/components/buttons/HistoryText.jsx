//not a button but goes to BookButtons
import {splitDate} from "../../utils/splitDate.js";

function HistoryText({book}) {

    return (
          <>
            <p className="text-sm mb-1 text-left">
              Borrowed: {splitDate(book.createdAt)}
            </p>
            <p className="text-sm mb-1 text-left">
              Returned: {splitDate(book.returnDate)}
            </p>
          </>
    )
}
export default HistoryText;