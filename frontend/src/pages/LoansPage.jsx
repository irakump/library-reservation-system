import BookDataPage from "./BookDataPage";
import { useLoanContext } from "../contexts/LoanContext.jsx";

const LoansPage = () => {
  const { loans } = useLoanContext();

  if (loans.length === 0 ) {
      return <div className="min-h-screen text-center p-10 bg-background">No loans yet!</div>
  }

  return (
    <div className="bg-background py-10 sm:py-20">
      <div className="gap-16 sm:gap-20 mx-auto sm:max-w-4xl sm:px-3">
        {loans && (
          <BookDataPage
            title={`My Loans (${loans.length})`}
            books={loans}
            pageType="loans"
          />
        )}
      </div>
      
    </div>
  );
};

export default LoansPage;
