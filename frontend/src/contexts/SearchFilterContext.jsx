import PropTypes from 'prop-types';
import { createContext, useContext, useState } from 'react';

const SearchFilterContext = createContext({
  searchFilters: {
    genres: [],
    languages: [],
    years: [],
    available: true,
    search_term: '',
  },
  setSearchFilters: () => {},
});

export const SearchFilterProvider = ({ children }) => {
  const [searchFilters, setSearchFilters] = useState({
    genres: [],
    languages: [],
    years: [],
    available: true,
    search_term: '',
  });

  return (
    <SearchFilterContext.Provider value={{ searchFilters, setSearchFilters }}>
      {children}
    </SearchFilterContext.Provider>
  );
};

SearchFilterProvider.propTypes = {
  children: PropTypes.node.isRequired,
};

export const useSearchFilters = () => useContext(SearchFilterContext);
