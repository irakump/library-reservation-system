import {createContext, useContext, useState} from 'react';
import axios from 'axios';
import { useSearchFilters } from './SearchFilterContext';
import i18n from "i18next";
import PropTypes from 'prop-types';

const SearchResultContext = createContext({
  searchResults: [],
  setSearchResults: () => {},
  fetchSearchResults: async () => {}
});

export const SearchResultProvider = ({ children }) => {
  const [searchResults, setSearchResults] = useState([]);
  const { searchFilters } = useSearchFilters();


  const fetchSearchResults = async () => {
    axios
      .get(`http://localhost:8081/api/book/filter/${i18n.language}`, { params: searchFilters })
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

SearchResultProvider.propTypes = {
  children: PropTypes.node.isRequired,
};

export const useSearchResult = () => useContext(SearchResultContext);
