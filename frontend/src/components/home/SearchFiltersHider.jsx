import SearchFilters from './SearchFilters';

const SearchFiltersHider = ({ filtersVisible, toggleFilters }) => {
  return (
    <div className="sm:px-12">
      {filtersVisible && <SearchFilters />}
      <div className="flex justify-end mr-2 sm:mr-1">
        <button onClick={toggleFilters} className="cursor-pointer">
          {filtersVisible ? 'Hide' : 'Show'} Filters
        </button>
      </div>
    </div>
  );
};

export default SearchFiltersHider;
