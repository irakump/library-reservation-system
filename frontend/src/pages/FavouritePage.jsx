import BookDataPage from "./BookDataPage";
import { useFavoritesContext } from "../contexts/FavoritesContext.jsx";

const FavouritePage = () => {
  const { favorites } = useFavoritesContext();

  if (favorites.length === 0) {
    return <div className="min-h-screen text-center p-10 bg-background">No favorites yet!</div>;
  }

  return (
    <div className="bg-background py-10 sm:py-20">
      <div className="gap-16 sm:gap-20 mx-auto sm:max-w-4xl sm:px-3">
        <h1 className="text-2xl font-bold text-center text-heading py-6 sm:py-8">My Favorites ({favorites.length})</h1>
          {favorites && (
            <BookDataPage
              title=""
              books={favorites}
              pageType="favourite"
            />
          )}
      </div>
    </div>
  );
};

export default FavouritePage;
