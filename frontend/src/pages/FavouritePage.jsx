import BookDataPage from "./BookDataPage";


const FavouritePage = () => {
  const favouriteData = [
    {
      id: 1,
      title: "Book Title1",
      author: "Author name",
      year: "2025",
      available: false,
    },
    {
      id: 2,
      title: "Book Title2",
      author: "Author name",
      year: "2025",
      available: false,
    },
    {
      id: 3,
      title: "Book Title3",
      author: "Author name",
      year: "2025",
      available: true,
    },
    {
      id: 4,
      title: "Book Title4",
      author: "Author name",
      year: "2025",
      available: true,
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
