import { useState } from 'react';
import FilterTag from './FilterTag';

const ActiveFilters = () => {
  const [activeFilters, setActiveFilters] = useState([
    'filterrrrrrrrrrrrrrrrrrr1',
    'filter2',
    'filter3',
    'filter4',
  ]);

  const removeFilter = (filterName) => {
    setActiveFilters(activeFilters.filter((name) => name !== filterName));
  };

  const clearFilters = () => {
    setActiveFilters([]);
  };

  return (
    <>
      <div className="flex flex-wrap gap-2">
        <FilterTag
          filterName={`Clear Filters`}
          closeButton={false}
          onRemove={clearFilters}
        />

        {activeFilters.map((filter) => (
          <FilterTag
            key={filter}
            filterName={filter}
            onRemove={() => removeFilter(filter)}
          />
        ))}
      </div>
    </>
  );
};

export default ActiveFilters;
