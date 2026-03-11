import BookDataPage from "./BookDataPage";
import { useLoanContext } from "../contexts/LoanContext.jsx";

const LoansPage = () => {
  const { loans } = useLoanContext();

  if (loans.length === 0 ) {
      return <div className="min-h-screen text-center p-10 bg-background">No loans yet!</div>
  }

  return (
    <div className="bg-background">
      <div className="mx-auto px-4 max-w-md sm:max-w-4xl lg:max-w-6xl pb-12 py-6 sm:py-8">
        {loans && (
          <BookDataPage
            title={`My Loans (${loans.length})`}
            books={loans}
            pageType="favourite"
          />
        )}
      </div>
      
    </div>
  );
};

export default LoansPage;
