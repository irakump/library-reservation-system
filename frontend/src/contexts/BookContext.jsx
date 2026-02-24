import {createContext, useState, useContext, useEffect} from "react";
import {addFavorite, getFavorites, removeFavorite} from "../api/favoritesApi.js";

// set defaults so tests wont crash
const BookContext = createContext({
    isFavorite: () => false,
    addToFavorites: async () => {},
    removeFromfavorites: async () => {},
})

export const useBookContext = () => useContext(BookContext)

export const BookProvider = ({children}) => {
    const [favorites, setFavorites] = useState([])
    const userId = 2; //for testing

    useEffect(() => {
        getFavorites(userId)
            .then(res => setFavorites(res.data));
    }, []);


    const isFavorite = (isbn) => {
        return favorites.includes(isbn)
    }

    const addToFavorites = async (isbn) => {
        await addFavorite(2, isbn).then(() => {
            setFavorites(prev => [...prev, isbn]);
        })
    }

    const removeFromfavorites = async (isbn) => {
        await removeFavorite(2, isbn);
        await setFavorites(prev => prev.filter(f => f !== isbn));
    }

    const value = {
        isFavorite,
        addToFavorites,
        removeFromfavorites,
    }

    return <BookContext.Provider value={value}>
        {children}
    </BookContext.Provider>
}