import axios from "axios";

const URL = "http://localhost:8081/api"


export function createLoan(userId, isbn) {
    axios.post( `${URL}/loans/new`, {
        userId,
        isbn
    },
        {headers: {
            'Content-Type': 'application/json'
            }
        }
    )
        .then(response => {
            console.log('create res: ', response)
            return response.data;
        })
        .catch(error => {
            console.log(error)
        })

}

export function returnLoan(userId, isbn, loanId) {
    axios.put(
        `${URL}/loans/return`, {
            userId,
            isbn,
            loanId
        }, {headers: {
            'Content-Type': "application/json"}
        }).then(response => {
            console.log('return: ', response)
    }).catch(error => {
        console.error(error)
    })

}

export const getLoans = async (userId) => {
    return await axios.get(`${URL}/loans/user/${userId}`)
}