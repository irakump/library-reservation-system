import {useBookContext} from "../../contexts/FavoritesContext.jsx";

const FavoriteButton = ({book}) => {
    const {isFavorite, addToFavorites, removeFromfavorites} = useBookContext()
    const favorite = isFavorite(book)
    //console.log(("Removefav: ", removeFromfavorites))

    function onFavoriteClick(e) {
        e.stopPropagation();
        if (favorite) removeFromfavorites(book)
        else addToFavorites(book)
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