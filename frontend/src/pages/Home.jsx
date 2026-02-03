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

      <div className="p-4 bg-background">
        <SearchBar />
        <SearchFiltersHider /> {/* Contains SearchFilters */}
        <SearchResults />
      </div>

      <Footer />
    </>
  );
};

export default Home;
