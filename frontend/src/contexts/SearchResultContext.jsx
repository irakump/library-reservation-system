import { createContext, useContext, useState } from 'react';
import axios from 'axios';
import { useSearchFilters } from './SearchFilterContext';

const SearchResultContext = createContext();

export const SearchResultProvider = ({ children }) => {
  const [searchResults, setSearchResults] = useState([]);
  const { searchFilters } = useSearchFilters();

  const fetchSearchResults = async () => {
    axios
      .get('http://localhost:8081/api/book/filter', { params: searchFilters })
      .then((response) => setSearchResults(response.data))
      .catch((error) => console.error(error));
  };

  return (
    <SearchResultContext.Provider
      value={{ searchResults, setSearchResults, fetchSearchResults }}
    >
      {children}
    </SearchResultContext.Provider>
  );
};

export const useSearchResult = () => useContext(SearchResultContext);
