import BookDataPage from "./BookDataPage";
import { useLoanContext } from "../contexts/LoanContext.jsx";

const HistoryPage = () => {
  const { history } = useLoanContext();

  if (history.length === 0) {
    return <div className="text-center p-10">No history yet!</div>;
  }

  return (
    <div className="mx-auto px-4">
      {history && (
        <BookDataPage
          title={`My History (${history.length})`}
          books={history}
          pageType="history"
        />
      )}
    </div>
  );
};

export default HistoryPage;
