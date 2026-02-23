import {createContext, useState, useContext, useEffect} from "react";
import {addFavorite, removeFavorite} from "../api/favoritesApi.js";

const BookContext = createContext()

export const useBookContext = () => useContext(BookContext)

export const BookProvider = ({children}) => {
    const [favorites, setFavorites] = useState([])

    useEffect(() => {
        const storedFavs = localStorage.getItem("favorites")

        if (storedFavs) {
            setFavorites(JSON.parse(storedFavs))
        }
    }, []);

    useEffect(() => {
        localStorage.setItem('favorites', JSON.stringify(favorites))
    }, [favorites])

    const isFavorite = (isbn) => {
        return favorites.some(f => f === isbn)
    }

    const addToFavorites = (isbn) => {
        addFavorite(2, isbn).then(() => setFavorites(isbn))
    }

    const removeFromfavorites = (isbn) => {
        removeFavorite(2, isbn)
    }

    const value = {
        isFavorite,
        addToFavorites,
        removeFromfavorites,
        useBookContext
    }

    return <BookContext.Provider value={value}>
        {children}
    </BookContext.Provider>
}