import BookDataPage from "./BookDataPage";
import { useEffect, useState} from "react";
import axios from "axios";


const FavouritePage = () => {
    const [favorites, setFavorites] = useState([]); //toiseen paikkaan
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


  //siirrä toiseen paikkaan useApi tms fetchiin parametri isbn + favorite context
    useEffect(() => {
        axios
            .get('http://localhost:8081/api/users/favorite') //korjaa oikea endpoint
            .then((response) => setFavorites(response.data))
            .catch((error) => console.error(error));
    }, []);

    console.log("books: ", favorites)



    return (
    <BookDataPage
      title="My Favourites (4)"
      books={favouriteData}
      pageType="favourite"
    />
  );
};

export default FavouritePage;
