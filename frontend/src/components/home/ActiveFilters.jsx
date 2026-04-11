import { useTranslation } from "react-i18next";
import FilterTag from "./FilterTag";
import PropTypes from "prop-types";

const ActiveFilters = ({ filters, genres, languages, onRemove }) => {
  const { t } = useTranslation("search");

  const getDisplayName = (filterType, filter) => {
    if (filterType === "genre") {
      const found = genres.find((g) => g.genreKey === filter);
      return found ? found.genre : filter;
    }

    if (filterType === "language") {
      const found = languages.find((l) => l.languageKey === filter);
      return found ? found.language : filter;
    }
    return filter;
  };

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
              filterName={getDisplayName(filterType, filter)}
              filterType={filterType}
              onRemove={() => onRemove(filter, filterType)}
            />
          )),
        )}
      </div>
    </>
  );
};

ActiveFilters.propTypes = {
  filters: PropTypes.object.isRequired,
  genres: PropTypes.array.isRequired,
  languages: PropTypes.array.isRequired,
  onRemove: PropTypes.func.isRequired,
};

export default ActiveFilters;
