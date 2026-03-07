import BookDataPage from "./BookDataPage";
import { useLoanContext } from "../contexts/LoanContext.jsx";

const LoansPage = () => {
  const { loans } = useLoanContext();

  return (
    <>
      {loans && (
        <BookDataPage title={`My Loans (${loans.length})`} books={loans} pageType="loans" />
      )}
    </>
  );
};

export default LoansPage;
