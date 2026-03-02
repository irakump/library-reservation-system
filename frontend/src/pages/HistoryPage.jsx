import BookDataPage from "./BookDataPage";
import {useLoanContext} from "../contexts/LoanContext.jsx";


const HistoryPage = () => {
    const {history} = useLoanContext();

    if(history.length === 0) {
        return <div className="text-center p-10"></div>
    }

  return history &&(
    <BookDataPage 
    title="My History" 
    books={history}
    pageType="history" 
    />
  );
};

export default HistoryPage;
