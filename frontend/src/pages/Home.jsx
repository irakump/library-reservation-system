import SearchBar from '../components/home/SearchBar';
import SearchResults from '../components/home/SearchResults';
import SearchFiltersHider from '../components/home/SearchFiltersHider';

const Home = () => {
  return (
    <>
      <h1>Home</h1>
      <p>Welcome to the home page!</p>
      <SearchBar />
      <SearchFiltersHider /> {/* Contains SearchFilters */}
      <SearchResults />
    </>
  );
};

export default Home;
