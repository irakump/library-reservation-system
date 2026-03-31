import BookDataPage from "./BookDataPage";
import { useLoanContext } from "../contexts/LoanContext.jsx";
import { useTranslation } from "react-i18next";

const HistoryPage = () => {
  const { history } = useLoanContext();
  const { t } = useTranslation("profile");

  if (history.length === 0) {
    return <div className="min-h-screen text-center p-10 bg-background">{t("history.title_none")}</div>;
  }

  return (
    <div className="bg-background py-10 sm:py-20">
      <div className="gap-16 sm:gap-20 mx-auto sm:max-w-4xl sm:px-3">
        {history && (
          <BookDataPage
            title={`${t("history.title", { count: history.length })}`}
            books={history}
            pageType="history"
          />
        )}
      </div>
    </div>
    
  );
};

export default HistoryPage;
