import { HeartIcon as HeartOutline } from "@heroicons/react/24/outline";
import { HeartIcon as HeartSolid } from "@heroicons/react/24/solid";
import { useFavoritesContext } from "../../contexts/FavoritesContext.jsx";
import PropTypes from "prop-types";

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
    <button onClick={(e) => onFavoriteClick(e)} className="text-xl">
      {favorite ? (
        <HeartSolid className="h-6 w-6 text-favourite hover:cursor-pointer" />
      ) : (
        <HeartOutline className="h-6 w-6 text-gray-800 hover:text-favourite hover:cursor-pointer" />
      )}
    </button>
  );
};

FavoriteButton.propTypes = {
  book: PropTypes.shape({
    isbn: PropTypes.string,
    bookIsbn: PropTypes.string,
  }).isRequired,
};

export default FavoriteButton;
