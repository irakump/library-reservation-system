import { createContext, useContext, useState } from "react";

const SearchResultContext = createContext();

export const SearchResultProvider = ({ children }) => {
  const [searchResults, setSearchResults] = useState([]);

  return (
    <SearchResultContext.Provider value={{ searchResults, setSearchResults }}>
      {children}
    </SearchResultContext.Provider>
  );
};

export const useSearchResult = () => useContext(SearchResultContext);