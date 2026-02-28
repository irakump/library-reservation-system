import BookDataPage from "./BookDataPage";
import {useLoanContext} from "../contexts/LoanContext.jsx";

const LoansPage = () => {

    const {loans} = useLoanContext()
    console.log(loans);

  return (
      <>
          { loans && (
              <BookDataPage
                  title="My loans"
                  books={loans}
                  pageType="loans"
              />
          )}
      </>

  );
};

export default LoansPage;
