import BookDataPage from "./BookDataPage";
import { useLoanContext } from "../contexts/LoanContext.jsx";

const LoansPage = () => {
  const { loans } = useLoanContext();

  if (loans.length === 0 ) {
      return <div className="min-h-screen text-center p-10">No loans yet!</div>
  }

  return (
    <div className="mx-auto px-4 max-w-md sm:max-w-4xl lg:max-w-6xl pb-12">
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
