import Footer from "../components/footer/Footer";
import SearchBar from "../components/home/SearchBar";
import SearchResults from '../components/home/SearchResults';
import SearchFiltersHider from '../components/home/SearchFiltersHider';
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
      <SearchFiltersHider /> {/* Contains SearchFilters */}
      <SearchResults />
      <Footer />
    </>
  );
};

export default Home;
