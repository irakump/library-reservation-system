import BookDataPage from "./BookDataPage";
import { useLoanContext } from "../contexts/LoanContext.jsx";

const HistoryPage = () => {
  const { history } = useLoanContext();

  if (history.length === 0) {
    return <div className="min-h-screen text-center p-10">No history yet!</div>;
  }

  return (
    <div className="mx-auto px-4 max-w-md sm:max-w-4xl lg:max-w-6xl pb-12">
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
