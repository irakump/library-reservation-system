import BookDataPage from "./BookDataPage";

const FavouritePage = () => {
  const favouriteData = [
    {
      id: 1,
      title: "Book Title",
      author: "Author name",
      year: "2025",
    },
    {
      id: 1,
      title: "Book Title",
      author: "Author name",
      year: "2025",
    },
    {
      id: 1,
      title: "Book Title",
      author: "Author name",
      year: "2025",
    },
    {
      id: 1,
      title: "Book Title",
      author: "Author name",
      year: "2025",
    },
  ];
  return (
    <BookDataPage
      title="My Favourites (4)"
      books={favouriteData}
      pageType="favourite"
    />
  );
};

export default FavouritePage;
