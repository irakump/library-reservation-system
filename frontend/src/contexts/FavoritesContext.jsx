import { createContext, useState, useContext, useEffect } from "react";
import {
  addFavorite,
  getFavorites,
  removeFavorite,
} from "../api/favoritesApi.js";
import { useAuth } from "./AuthContext.jsx";

// set defaults so tests wont crash
const FavoritesContext = createContext({
  isFavorite: () => false,
  addToFavorites: async () => {},
  removeFromfavorites: async () => {},
  favourites: [],
});

export const useFavoritesContext = () => useContext(FavoritesContext);

export const FavoritesProvider = ({ children }) => {
  const [favorites, setFavorites] = useState([]);
  const { user, isLoggedIn } = useAuth();

  useEffect(() => {
    if (isLoggedIn && user?.userId) {
      getFavorites(user.userId)
        .then((res) => setFavorites(res.data))
        .catch((error) => console.error("Error fetching favorites: ", error));
    }
  }, [isLoggedIn, user]);

  const isFavorite = (isbn) => {
    return favorites.includes(isbn);
  };

  const addToFavorites = async (isbn) => {
    if (!user?.userId) {
      console.error("User not logged in");
      return;
    }

    await addFavorite(user.userId, isbn).then(() => {
      setFavorites((prev) => [...prev, isbn]);
    });
  };

  const removeFromfavorites = async (isbn) => {
    if (!user?.userId) {
      console.error("User not logged in");
      return;
    }

    await removeFavorite(user.userId, isbn);
    setFavorites((prev) => prev.filter((f) => f !== isbn));
  };

  const value = {
    isFavorite,
    addToFavorites,
    removeFromfavorites,
    favorites,
  };

  return (
    <FavoritesContext.Provider value={value}>
      {children}
    </FavoritesContext.Provider>
  );
};
