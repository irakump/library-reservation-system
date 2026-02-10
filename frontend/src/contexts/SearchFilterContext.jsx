import { createContext, useContext, useState } from "react";

const SearchFilterContext = createContext();

export const SearchFilterProvider = ({ children }) => {
  const [searchFilters, setSearchFilters] = useState({
    genres: [],
    languages: [],
    years: [],
    available: true,
    title_author: "",
  });

  return (
    <SearchFilterContext.Provider value={{ searchFilters, setSearchFilters }}>
      {children}
    </SearchFilterContext.Provider>
  );
};

export const useSearchFilters = () => useContext(SearchFilterContext);
