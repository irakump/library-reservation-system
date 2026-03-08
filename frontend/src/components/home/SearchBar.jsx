import { useState } from 'react';
import { useSearchResult } from '../../contexts/SearchResultContext';
import { useSearchFilters } from '../../contexts/SearchFilterContext';

import MagnifyingGlass from '@heroicons/react/16/solid/MagnifyingGlassIcon';
import XMark from '@heroicons/react/24/solid/XMarkIcon';

const SearchBar = () => {
  const { fetchSearchResults } = useSearchResult(); // context
  const { searchFilters, setSearchFilters } = useSearchFilters();

  const [searchTerm, setSearchTerm] = useState('');

  const handleSearch = () => {
    setSearchFilters({ ...searchFilters, search_term: searchTerm });
    console.log('Search Filters:', searchFilters);

    fetchSearchResults();
  };

  const emptySearchBar = () => {
    setSearchTerm('');
  };

  return (
    <div className="flex flex-col w-full h-16 sm:h-20 gap-1 px-6 sm:px-12">
      <label className="ml-1" htmlFor="search">
        Search
      </label>

      <form
        onSubmit={(e) => {
          e.preventDefault();
          handleSearch();
        }}
        className="flex w-full h-10 rounded border border-black divide-x-2 divide-black overflow-hidden"
      >
        <div className="relative flex-1">
          <input
            type="text"
            id="search"
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
            className="w-full h-full px-3 py-2 bg-white outline-none"
            placeholder="Search for books"
          />
          {searchTerm && (
            <button
              type="button"
              onClick={emptySearchBar}
              className="absolute inset-y-0 right-2 flex items-center cursor-pointer"
            >
              <XMark className="h-full size-6 text-gray-500 hover:text-black" />
            </button>
          )}
        </div>

        <button
          type="submit"
          className="flex items-center px-4 gap-2 bg-filter text-black hover:bg-sky-500 cursor-pointer"
        >
          <MagnifyingGlass className="h-full size-5" />
          Search
        </button>
      </form>
    </div>
  );
};

export default SearchBar;
