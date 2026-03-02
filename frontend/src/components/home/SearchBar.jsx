import { useEffect, useState } from 'react';
import { useSearchResult } from '../../contexts/SearchResultContext';
import { useSearchFilters } from '../../contexts/SearchFilterContext';

import MagnifyingGlass from '@heroicons/react/16/solid/MagnifyingGlassIcon';

const SearchBar = () => {
  const { fetchSearchResults } = useSearchResult(); // context
  const { searchFilters, setSearchFilters } = useSearchFilters();

  const [searchTerm, setSearchTerm] = useState(null);

  const handleSearch = () => {
    setSearchFilters({ ...searchFilters, search_term: searchTerm });
    console.log('Search Filters:', searchFilters);

    fetchSearchResults();
  };

  return (
    <div className="flex flex-col w-full h-15 gap-1 mb-12 p-4 sm:p-0 mx-auto sm:max-w-xl">
      <label className="ml-1" htmlFor="search">
        Search
      </label>

      <form
        onSubmit={(e) => {
          e.preventDefault();
          handleSearch();
        }}
        className="flex flex-row flex-1 w-full h-full rounded-sm border border-black divide-x-2 divide-black"
      >
        <input
          type="text"
          id="search"
          onChange={(e) => e.target.value !== "" ? setSearchTerm(e.target.value) : null}
          className="flex-1 h-full bg-white px-1"
        />

        <button
          type="submit"
          onClick={handleSearch}
          className="flex flex-row h-full px-4 bg-filter gap-1 cursor-pointer items-center"
        >
          <MagnifyingGlass className="h-full size-4" />
          Search
        </button>
      </form>
    </div>
  );
};

export default SearchBar;
