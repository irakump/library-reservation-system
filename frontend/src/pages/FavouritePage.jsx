import BookDataPage from "./BookDataPage";
import {useFavoritesContext} from "../contexts/FavoritesContext.jsx";

const FavouritePage = () => {
    const {favorites} = useFavoritesContext()


    return favorites && (
    <BookDataPage
      title="My Favourites (4)"
      books={favorites}
      pageType="favourite"
    />
  );
};

export default FavouritePage;
