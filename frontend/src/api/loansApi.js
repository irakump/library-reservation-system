import axios from "axios";

const URL = "http://localhost:8081/api"

export const getLoanByUserId = async (userID) => {
    const response = await axios.get(`${URL}/user/${userID}`);
    return response.data;
}

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
            console.log('create res: ', response.data)
        })
        .catch(error => {
            console.log(error)
        })
}

//export function returnLoan(userId, isbn) {
//    axios.put(
//        `${URL}`
//
//}