import {createContext, useState, useContext, useEffect} from "react";
import {addFavorite, getFavorites, removeFavorite} from "../api/favoritesApi.js";

// set defaults so tests wont crash
const FavoritesContext = createContext({
    isFavorite: () => false,
    addToFavorites: async () => {},
    removeFromfavorites: async () => {},
})

export const useBookContext = () => useContext(FavoritesContext)

export const BookProvider = ({children}) => {
    const [favorites, setFavorites] = useState([])
    const userId = 2; //for testing

    useEffect(() => {
        getFavorites(userId)
            .then(res => setFavorites(res.data));
    }, []);


    const isFavorite = (book) => {
        return favorites.includes(book)
    }

    const addToFavorites = async (book) => {
        await addFavorite(2, book.isbn).then(() => {
            setFavorites(prev => [...prev, book]);
        })
    }

    const removeFromfavorites = async (book) => {
        await removeFavorite(2, book.isbn);
        await setFavorites(prev => prev.filter(f => f !== book));
    }

    const value = {
        isFavorite,
        addToFavorites,
        removeFromfavorites,
    }

    return <FavoritesContext.Provider value={value}>
        {children}
    </FavoritesContext.Provider>
}