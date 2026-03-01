import {useFavoritesContext} from "../../contexts/UseFavoritesContext.jsx";

const FavoriteButton = ({book}) => {
    const {isFavorite, addToFavorites, removeFromfavorites} = useFavoritesContext()
    const favorite = isFavorite(book.isbn)
    //console.log(("Removefav: ", removeFromfavorites))

    function onFavoriteClick(e) {
        e.stopPropagation();
        if (favorite) removeFromfavorites(book.isbn)
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