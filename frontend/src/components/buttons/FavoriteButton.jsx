import { useFavoritesContext } from "../../contexts/FavoritesContext.jsx";

const FavoriteButton = ({ book }) => {
  const { isFavorite, addToFavorites, removeFromfavorites } =
    useFavoritesContext();

  const isbn = book.isbn ?? book.bookIsbn; // With reservations, use bookIsbn

  const favorite = isFavorite(isbn);

  function onFavoriteClick(e) {
    e.stopPropagation();

    if (favorite) {
      removeFromfavorites(book.isbn ?? book.bookIsbn);
    } else {
      addToFavorites({ ...book, isbn }); // New book object to include also reservations bookIsbn as isbn
    }
  }

  return (
    <button onClick={(e) => onFavoriteClick(e)}>{favorite ? "♥︎" : "♡"}</button>
  );
};

export default FavoriteButton;
