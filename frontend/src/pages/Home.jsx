import Footer from '../components/footer/Footer';
import SearchBar from '../components/home/SearchBar';
import SearchResults from '../components/home/SearchResults';
import SearchFiltersHider from '../components/home/SearchFiltersHider';
import { useEffect, useState } from 'react';
import axios from 'axios';


const Home = () => {

  // example, delete
  const [genres, setGenres] = useState([]);
  const [users, setUsers] = useState([]);

  useEffect(() => {
    axios
      .get('http://localhost:8081/api/genre')
      .then((response) => setGenres(response.data))
      .catch((error) => console.error(error));
  
    }, []);

  console.log('Genres:', genres);


    useEffect(() => {
      axios
      .get('http://localhost:8081/api/users')
      .then((response) => setUsers(response.data))
      .catch((error) => console.error(error));
    }, []);

  console.log('Users: ', users);
  //

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
