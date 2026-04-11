import { createContext, useState, useContext, useEffect } from "react";
import {
  addFavorite,
  getFavorites,
  removeFavorite,
} from "../api/favoritesApi.js";
import { useAuth } from "./AuthContext.jsx";
import PropTypes from "prop-types";

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
        .then((res) => {
          setFavorites(res.data);
        })
        .catch((error) => console.error("Error fetching favorites: ", error));
    }
  }, [isLoggedIn, user]);

  const isFavorite = (isbn) => {
    return favorites.some((book) => book.isbn === isbn);
  };

  const addToFavorites = async (book) => {
    if (!user?.userId) {
      console.error("User not logged in");
      return;
    }

    try {
      await addFavorite(user.userId, book.isbn);

      setFavorites((prev) => [...prev, book]);
    } catch (error) {
      console.error("Error adding favorite: ", error);
    }
  };

  const removeFromfavorites = async (isbn) => {
    if (!user?.userId) {
      console.error("User not logged in");
      return;
    }

    try {
      await removeFavorite(user.userId, isbn);

      setFavorites((prev) => prev.filter((book) => book.isbn !== isbn));
    } catch (error) {
      console.error("Error removing favourite:", error);
    }
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

FavoritesProvider.propTypes = {
  children: PropTypes.node.isRequired,
};
