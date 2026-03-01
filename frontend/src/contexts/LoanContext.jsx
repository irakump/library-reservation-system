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

  export const useLoanContext = () => useContext(LoanContext);

  export const LoanProvider = ({ children }) => {
    const [loans, setLoans] = useState([]);
    const userId = 2;

    useEffect(() => {
      getLoans(userId).then((res) => setLoans(res.data));
    }, []);

    const addToLoans = async (isbn) => {
      const response = await createLoan(userId, isbn);
      await setLoans((prev) => [...prev, response]);
    };
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

  return <LoanContext.Provider value={value}>{children}</LoanContext.Provider>;
};
