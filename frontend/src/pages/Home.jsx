import Footer from "../components/footer/Footer";
import SearchBar from "../components/home/SearchBar";
import SearchResults from '../components/home/SearchResults';
import SearchFiltersHider from '../components/home/SearchFiltersHider';


const Home = () => {
  return (
    <>

      <div className="p-4 bg-background">
        <SearchBar />
        <SearchFiltersHider /> {/* Contains SearchFilters */}
        <SearchResults />
      </div>
    </>
  );
};

export default Home;
