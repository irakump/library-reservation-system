import AvailableFilter from "../components/home/FilterAvailable";
import SearchBar from "../components/home/SearchBar";
import { SearchResults } from "../components/home/SearchResults";
import NavBar from "../components/navbar/NavBar";
import { MenuProvider } from "../contexts/MenuContext";

const Home = () => {
  return (
    <>
      <MenuProvider>
        <NavBar />
      </MenuProvider>

      <h1>Home</h1>
      <p>Welcome to the home page!</p>

      <SearchBar />
      <AvailableFilter />
      <SearchResults />
    </>
  );
};

export default Home;
