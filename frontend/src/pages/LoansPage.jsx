import BookDataPage from "./BookDataPage";
import { useLoanContext } from "../contexts/LoanContext.jsx";
import { useTranslation } from "react-i18next";

const LoansPage = () => {
  const { loans } = useLoanContext();
  const { t } = useTranslation("profile");

  if (loans.length === 0 ) {
      return <div className="min-h-screen text-center p-10 bg-background">{t("loans.title_none")}</div>
  }

  return (
    <div className="bg-background py-10 sm:py-20">
      <div className="gap-16 sm:gap-20 mx-auto sm:max-w-4xl sm:px-3">
        {loans && (
          <BookDataPage
            title={`${t("loans.title", { count: loans.length })}`}
            books={loans}
            pageType="loans"
          />
        )}
      </div>
      
    </div>
  );
};

export default LoansPage;
