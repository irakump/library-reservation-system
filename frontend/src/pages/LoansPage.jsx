import BookDataPage from "./BookDataPage";
import { useLoanContext } from "../contexts/LoanContext.jsx";

const LoansPage = () => {
  const { loans } = useLoanContext();

  return (
    <div className="mx-auto px-4 ">
      {loans && (
        <BookDataPage
          title={`My Loans (${loans.length})`}
          books={loans}
          pageType="loans"
        />
      )}
    </div>
  );
};

export default LoansPage;
