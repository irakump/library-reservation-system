import { useTranslation } from 'react-i18next';
import FilterTag from './FilterTag';

const ActiveFilters = ({ filters, onRemove }) => {
  const { t } = useTranslation("search");
  return (
    <>
      <div className="flex flex-wrap gap-2">
        {/* remove all filters */}
        <FilterTag
          filterName={t("filters.clear_filters_btn")}
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
