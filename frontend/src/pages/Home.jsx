import AvailableFilter from '../components/home/FilterAvailable';
import SearchBar from '../components/home/SearchBar';
import { SearchResults } from '../components/home/SearchResults';

const Home = () => {
  return (
    <>
      <h1>Home</h1>
      <p>Welcome to the home page!</p>

      <SearchBar />
      <AvailableFilter />
      <SearchResults />
    </>
  );
};

export default Home;
