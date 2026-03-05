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

export const useSearchFilters = () => useContext(SearchFilterContext);
