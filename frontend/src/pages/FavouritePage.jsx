import BookDataPage from "./BookDataPage";
import { useFavoritesContext } from "../contexts/FavoritesContext.jsx";

const FavouritePage = () => {
  const { favorites } = useFavoritesContext();
  const length = favorites.length;

  if (!favorites || favorites.length === 0) {
    return <div className="text-center p-10">No favorites yet!</div>;
  }

  return (
    <div className="mx-auto px-4 ">
      {favorites && (
        <BookDataPage
          title={`My Favorites (${length})`}
          books={favorites}
          pageType="favourite"
        />
      )}
    </div>
  );
};

export default FavouritePage;
