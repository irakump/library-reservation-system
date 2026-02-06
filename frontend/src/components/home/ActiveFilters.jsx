import FilterTag from './FilterTag';

const ActiveFilters = ({ filters, onRemove }) => {
  return (
    <>
      <div className="flex flex-wrap gap-2">
        {/* remove all filters */}
        <FilterTag
          filterName={`Clear Filters`}
          closeButton={false}
          onRemove={() => onRemove(null, null)}
        />

        {/* individual filters */}
        {Object.entries(filters).map(([filterType, filters]) =>
          filters.map((filter) => (
            <FilterTag
              key={filter}
              filterName={filter}
              filterType={filterType}
              onRemove={() => onRemove(filter, filterType)}
            />
          )),
        )}
      </div>
    </>
  );
};

export default ActiveFilters;
