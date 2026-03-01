import Footer from "../components/footer/Footer";
import SearchBar from "../components/home/SearchBar";
import SearchResults from "../components/home/SearchResults";
import SearchFiltersHider from "../components/home/SearchFiltersHider";

import { SearchResultProvider } from "../contexts/SearchResultContext";
import { SearchFilterProvider } from "../contexts/SearchFilterContext";

const Home = () => {
  return (
    <>
      <SearchFilterProvider>
        <SearchResultProvider>
          <div className="p-4 bg-background">
            <SearchBar />
            <SearchFiltersHider /> {/* Contains SearchFilters */}
            <SearchResults />
          </div>
        </SearchResultProvider>
      </SearchFilterProvider>
    </>
  );
};

export default Home;
