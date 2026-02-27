import BookDataPage from "./BookDataPage";
import {useApi} from "../hooks/useApi.js";

const LoansPage = () => {
    const userID = 2;

    const {data, loading} = useApi(`/loans/user/${userID}`);

  return (
      <>
          {!loading && data && (
              <BookDataPage
                  title="My loans"
                  books={data}
                  pageType="loans"
              />
          )}
      </>

  );
};

export default LoansPage;
