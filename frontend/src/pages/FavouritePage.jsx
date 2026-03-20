import BookDataPage from "./BookDataPage";
import { useFavoritesContext } from "../contexts/FavoritesContext.jsx";
import { useTransition } from "react";

const FavouritePage = () => {
  const { favorites } = useFavoritesContext();
  const { t } = useTransition("profile");

  if (favorites.length === 0) {
    return <div className="min-h-screen text-center p-10 bg-background">{t("favorites.title_none")}</div>;
  }

  return (
    <div className="bg-background py-10 sm:py-20">
      <div className="gap-16 sm:gap-20 mx-auto sm:max-w-4xl sm:px-3">
        <h1 className="text-2xl font-bold text-center text-heading py-6 sm:py-8">${t("favorites.title")} ({favorites.length})</h1>
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
