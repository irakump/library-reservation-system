import {useBookContext} from "../../contexts/BookContext.jsx";

const FavoriteButton = ({book}) => {
    const {isFavorite, addToFavorites, removeFromFavorites} = useBookContext()
    const favorite = isFavorite(book.isbn)

    function onFavoriteClick(e) {
        e.stopPropagation();
        if (favorite) removeFromFavorites(book.isbn)
        else addToFavorites(book.isbn)
    }

    return (
        <button
            onClick={(e) => onFavoriteClick(e)}
                >
             {favorite ? "♥︎" : "♡"}
        </button>
    )
}
export default FavoriteButton;