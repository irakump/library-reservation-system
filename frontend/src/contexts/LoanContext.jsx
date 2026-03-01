import { createContext, useContext, useEffect, useState } from "react";
import { createLoan, getLoans, returnLoan } from "../api/loansApi.jsx";
import { useAuth } from "./AuthContext.jsx";

const LoanContext = createContext({});

export const useLoanContext = () => useContext(LoanContext);

export const LoanProvider = ({ children }) => {
  const [loans, setLoans] = useState([]);
  const [trigger, setTrigger] = useState(false);
  const { user, isLoggedIn } = useAuth();

  useEffect(() => {
    // Only fetch if user is logged in
    if (isLoggedIn && user?.userId) {
      getLoans(user.userId)
        .then((res) => setLoans(res.data))
        .catch((error) => console.error("Error fetching loans: ", error));
    }
  }, [isLoggedIn, user, trigger]);

  const addToLoans = async (isbn) => {
    if (!user?.userId) {
      console.error("User not logged in");
      return;
    }

    await createLoan(user.userId, isbn);
    setTrigger(!trigger);
  };

  const removeLoans = async (userId, isbn, loanId) => {
    await returnLoan(userId, isbn, loanId);
    //setLoans(prev => prev.filter(f => f.loanId !== loanId));
    setTrigger(!trigger);
  };

  const value = {
    addToLoans,
    removeLoans,
    loans,
    setTrigger,
    trigger,
  };

  return <LoanContext value={value}>{children}</LoanContext>;
};
