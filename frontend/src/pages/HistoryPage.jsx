import BookDataPage from "./BookDataPage";
import {useLoanContext} from "../contexts/LoanContext.jsx";


const HistoryPage = () => {
    const {history} = useLoanContext();

    if(history.length === 0) {
        return <div className="text-center p-10">No history yet!</div>
    }

  return history &&(
    <BookDataPage 
    title={`My History (${history.length})`}
    books={history}
    pageType="history" 
    />
  );
};

export default HistoryPage;
