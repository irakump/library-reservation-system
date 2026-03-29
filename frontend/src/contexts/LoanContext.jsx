import { createContext, useContext, useEffect, useState } from "react";
import {
  createLoan,
  getHistory,
  getLoans,
  returnLoan,
} from "../api/loansApi.jsx";
import { useAuth } from "./AuthContext.jsx";

const LoanContext = createContext({});

export const useLoanContext = () => useContext(LoanContext);

export const LoanProvider = ({ children }) => {
  const [loans, setLoans] = useState([]);
  const { user, isLoggedIn } = useAuth();
  const [history, setHistory] = useState([]);

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
      alert("Please log in to loan or reserve books");
      return;
    }
    try {
      const response = await createLoan(user.userId, isbn); //Use JWT userId
      setLoans((prev) => [...prev, response]);
      alert(`${response.title} loaned`);
      return response;
    } catch (error) {
      console.error("Error creating loan: ", error);
    }
  };

  const removeLoans = async (userId, isbn, loanId) => {
    try {
      const loan = loans.find((l) => l.loanId === loanId); // Find loan to get book title
      const title = loan.title || "Book"; // Fallback title is Book

      await returnLoan(userId, isbn, loanId);
      setLoans((prev) => prev.filter((f) => f.loanId !== loanId));
      alert(`${title} returned`);
    } catch (error) {
      console.error("Error returning loan: ", error);
      alert("Error while returning loan. Please try again.")
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
