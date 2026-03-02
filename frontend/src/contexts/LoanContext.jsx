import { createContext, useContext, useEffect, useState } from "react";
import {createLoan, getHistory, getLoans, returnLoan} from "../api/loansApi.jsx";
import { useAuth } from "./AuthContext.jsx";

const LoanContext = createContext({});

export const useLoanContext = () => useContext(LoanContext);

export const LoanProvider = ({ children }) => {
  const [loans, setLoans] = useState([]);
  const { user, isLoggedIn } = useAuth();
  const [history, setHistory] = useState([])

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
      return;
    }
    try {
      const response = await createLoan(user.userId, isbn); //Use JWT userId
        setLoans((prev) => [...prev, response]);
    } catch (error) {
      console.error("Error creating loan: ", error);
    }
  };

  const removeLoans = async (userId, isbn, loanId) => {
    try {
      await returnLoan(userId, isbn, loanId);
      setLoans(prev => prev.filter(f => f.loanId !== loanId));

    } catch (error) {
      console.error("Error returning loan: ", error);
    }
  };

    useEffect(() => {
        if (isLoggedIn && user?.userId)
            getHistory(user.userId)
                .then((res) => setHistory(res.data))
                .catch((error) => console.error(error))
    }, [isLoggedIn, user]);


  const value = {
    addToLoans,
    removeLoans,
    loans,
      history,
  };

  return <LoanContext.Provider value={value}>{children}</LoanContext.Provider>;
};
