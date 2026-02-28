import {createContext, useContext, useEffect, useState} from "react";
import {createLoan, getLoans, returnLoan} from "../api/loansApi.jsx";



const LoanContext = createContext(({

}))

export const useLoanContext = () => useContext(LoanContext)

export const LoanProvider = ({children}) => {
    const [loans, setLoans] = useState([])
    const [trigger, setTrigger] = useState(false);
    const userId = 2;

    useEffect(() => {
        getLoans(userId)
            .then(res => setLoans(res.data));
    }, []);

    const addToLoans = async (isbn) => {
        await createLoan(userId, isbn)
        //setTrigger(!trigger);
    }

    const removeLoans = async (userId, isbn, loanId) => {
        await returnLoan(userId, isbn, loanId)
        //setLoans(prev => prev.filter(f => f.loanId !== loanId));
        setTrigger(!trigger);
    }


    const value = {
        addToLoans,
        removeLoans,
        loans,
        setTrigger,
        trigger
    }

    return <LoanContext value={value}>
        {children}
    </LoanContext>

}