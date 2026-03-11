import BookDataPage from "./BookDataPage";
import { useFavoritesContext } from "../contexts/FavoritesContext.jsx";

const FavouritePage = () => {
  const { favorites } = useFavoritesContext();

  if (favorites.length === 0) {
    return <div className="min-h-screen text-center p-10 bg-background">No favorites yet!</div>;
  }

  return (
     <div className="bg-background">
       <h1 className="text-2xl font-bold text-center text-heading py-6 sm:py-8">My Favorites ({favorites.length})</h1>
        <div className="mx-auto px-4 max-w-md sm:max-w-4xl lg:max-w-6xl pb-12">
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
