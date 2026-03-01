import {createContext, useState, useContext, useEffect} from "react";
import {addFavorite, getFavorites, removeFavorite} from "../api/favoritesApi.js";

// set defaults so tests wont crash
const FavoritesContext = createContext({
    isFavorite: () => false,
    addToFavorites: async () => {},
    removeFromfavorites: async () => {},
})

export const useFavoritesContext = () => useContext(FavoritesContext)

export const FavoritesProvider = ({children}) => {
    const [favorites, setFavorites] = useState([])
    const userId = 2; //for testing

    useEffect(() => {
        getFavorites(userId)
            .then(res => setFavorites(res.data));
    }, []);


    const isFavorite = (isbn) => {
        return favorites.some(f => f.isbn === isbn)
    }

    const addToFavorites = async (book) => {
        await addFavorite(2, book.isbn).then(() => {
            setFavorites(prev => [...prev, book]);
        })
    }

    const removeFromfavorites = async (isbn) => {
        await removeFavorite(2, isbn);
        await setFavorites(prev => prev.filter(f => f.isbn !== isbn));
    }

    const value = {
        isFavorite,
        addToFavorites,
        removeFromfavorites,
        favorites
    }

    return <FavoritesContext.Provider value={value}>
        {children}
    </FavoritesContext.Provider>
}