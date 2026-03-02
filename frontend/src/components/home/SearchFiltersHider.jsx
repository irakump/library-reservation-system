import { useState } from 'react';
import SearchFilters from './SearchFilters';

const SearchFiltersHider = () => {
  const [filtersVisible, setFiltersVisible] = useState(true);

  const toggleFilters = () => {
    setFiltersVisible(!filtersVisible);
  };

  return (
    <div className="mb-8 sm:max-w-4xl mx-auto">
      {filtersVisible && <SearchFilters />}
      <div className="flex justify-end mr-2 sm:mr-1">
        <button onClick={toggleFilters} className='cursor-pointer'>
          {filtersVisible ? 'Hide' : 'Show'} Filters
        </button>
      </div>
    </div>
  );
};

export default SearchFiltersHider;
