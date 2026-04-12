import { useState } from 'react';

import SearchBar from '../components/home/SearchBar';
import SearchResults from '../components/home/SearchResults';
import SearchFiltersHider from '../components/home/SearchFiltersHider';

import { SearchResultProvider } from '../contexts/SearchResultContext';
import { SearchFilterProvider } from '../contexts/SearchFilterContext';

const Home = () => {
  const [filtersVisible, setFiltersVisible] = useState(true);

  const toggleFilters = () => {
    setFiltersVisible(!filtersVisible);
  };

  return (
      <SearchFilterProvider>
        <SearchResultProvider>
          <div className="bg-background py-10 sm:py-20">
            <div className="flex flex-col gap-16 sm:gap-20 mx-auto sm:max-w-4xl sm:px-3">
              <SearchBar />
              <SearchFiltersHider filtersVisible={filtersVisible} toggleFilters={toggleFilters}/> {/* Contains SearchFilters */}
              <SearchResults />
            </div>
          </div>
        </SearchResultProvider>
      </SearchFilterProvider>

  );
};

export default Home;
