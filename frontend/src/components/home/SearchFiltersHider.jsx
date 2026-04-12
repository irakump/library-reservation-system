import { useTranslation } from 'react-i18next';
import SearchFilters from './SearchFilters';
import PropTypes from 'prop-types';

const SearchFiltersHider = ({ filtersVisible, toggleFilters }) => {
  const { t } = useTranslation('search');

  return (
    <div className="sm:px-12">
      {filtersVisible && <SearchFilters />}
      <div className="flex justify-end mr-2 sm:mr-1">
        <button onClick={toggleFilters} className="cursor-pointer">
          {filtersVisible ? t('filters_hide') : t('filters_show')}
        </button>
      </div>
    </div>
  );
};

SearchFiltersHider.propTypes = {
  filtersVisible: PropTypes.bool.isRequired,
  toggleFilters: PropTypes.func.isRequired,
};

export default SearchFiltersHider;
