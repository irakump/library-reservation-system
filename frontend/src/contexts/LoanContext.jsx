import { createContext, useContext, useEffect, useState } from "react";
import {
  createLoan,
  getHistory,
  getLoans,
  returnLoan,
} from "../api/loansApi.jsx";
import { useAuth } from "./AuthContext.jsx";
import { useTranslation } from "react-i18next";

// set defaults so tests wont crash
const LoanContext = createContext({
  addToLoans: async () => {},
  removeLoans: async () => {},
  loans: [],
  history: [],
});

export const useLoanContext = () => useContext(LoanContext);

export const LoanProvider = ({ children }) => {
  const [loans, setLoans] = useState([]);
  const { user, isLoggedIn } = useAuth();
  const [history, setHistory] = useState([]);
  const { t } = useTranslation('common');

  useEffect(() => {
    // Only fetch if user is logged in
    if (isLoggedIn && user?.userId) {
      getLoans(user.userId)
        .then((res) => setLoans(res.data))
        .catch((error) => console.error("Error fetching loans: ", error));
    }
  }, [isLoggedIn, user]);

  const addToLoans = async (isbn) => {
    if (!user?.userId) {
      console.error("User not logged in");
      alert(t("common:log_in_alert"));
      return;
    }
    try {
      const response = await createLoan(user.userId, isbn); //Use JWT userId
      setLoans((prev) => [...prev, response]);
      alert(`${response.title} ${t("loaned")}`);
      return response;
    } catch (error) {
      console.error("Error creating loan: ", error);
    }
  };

  const removeLoans = async (userId, isbn, loanId) => {
    try {
      const loan = loans.find((l) => l.loanId === loanId); // Find loan to get book title
      const title = loan.title || t("book"); // Fallback title is Book

      await returnLoan(userId, isbn, loanId);
      setLoans((prev) => prev.filter((f) => f.loanId !== loanId));
      alert(`${title} ${t("returned")}`);
    } catch (error) {
      console.error("Error returning loan: ", error);
      alert(t("common:returning_error"))
    }
  };

  useEffect(() => {
    if (isLoggedIn && user?.userId)
      getHistory(user.userId)
        .then((res) => setHistory(res.data))
        .catch((error) => console.error(error));
  }, [isLoggedIn, user]);

  const value = {
    addToLoans,
    removeLoans,
    loans,
    history,
  };

  return <LoanContext.Provider value={value}>{children}</LoanContext.Provider>;
};
