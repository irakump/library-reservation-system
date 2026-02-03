import { useState } from 'react';
import SearchFilters from './SearchFilters';

const SearchFiltersHider = () => {
  const [filtersVisible, setFiltersVisible] = useState(true);

  const toggleFilters = () => {
    setFiltersVisible(!filtersVisible);
  };

  return (
    <div className="mb-8">
      {filtersVisible && <SearchFilters />}
      <div className="flex justify-end mr-1">
        <button onClick={toggleFilters}>
          {filtersVisible ? 'Hide' : 'Show'} Filters
        </button>
      </div>
    </div>
  );
};

export default SearchFiltersHider;
