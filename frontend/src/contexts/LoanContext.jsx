import {createContext, useContext, useEffect, useState} from "react";
import {createLoan, getLoans, returnLoan} from "../api/loansApi.jsx";



const LoanContext = createContext(({
}))

export const useLoanContext = () => useContext(LoanContext)

export const LoanProvider = ({children}) => {
    const [loans, setLoans] = useState([])
    const userId = 2;

    useEffect(() => {
        getLoans(userId)
            .then(res => setLoans(res.data));
    }, []);

    const addToLoans = async (isbn) => {
        const response = await createLoan(userId, isbn)
        await setLoans(prev => [...prev, response])

    }

    const removeLoans = async (userId, isbn, loanId) => {
        await returnLoan(userId, isbn, loanId)
        await setLoans(prev => prev.filter(f => f.loanId !== loanId));
    }

    const value = {
        addToLoans,
        removeLoans,
        loans,
    }

    return <LoanContext value={value}>
        {children}
    </LoanContext>

}