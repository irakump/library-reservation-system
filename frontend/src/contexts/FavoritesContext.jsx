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
});

export const useBookContext = () => useContext(FavoritesContext);

export const BookProvider = ({ children }) => {
  const [favorites, setFavorites] = useState([]);
  const { user, isLoggedIn } = useAuth();
  //const userId = 2; //for testing

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
    await setFavorites((prev) => prev.filter((f) => f !== isbn));
  };

  const value = {
    isFavorite,
    addToFavorites,
    removeFromfavorites,
  };

  return (
    <FavoritesContext.Provider value={value}>
      {children}
    </FavoritesContext.Provider>
  );
};
