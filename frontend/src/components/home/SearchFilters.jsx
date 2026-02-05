import ActiveFilters from './ActiveFilters';
import { useState } from 'react';

const SearchFilters = () => {
  const mockCategories = [
    'Fiction',
    'Non-Fiction',
    'Science Fiction',
    'Fantasy',
  ];
  const mockLanguages = ['English', 'Finnish'];
  const mockYears = [2020, 2021, 2022, 2023];

  const filterTypes = {
    categories: 'category',
    languages: 'language',
    years: 'year',
  };

  const [selectedCategories, setSelectedCategories] = useState([]);
  const [selectedLanguages, setSelectedLanguages] = useState([]);
  const [selectedYears, setSelectedYears] = useState([]);

  const addNewFilter = (filterSetter, filters, newFilter) => {
    if (filters.includes(newFilter)) return;
    filterSetter([...filters, newFilter]);
  };

  const addFilter = (filter, filterType) => {
    switch (filterType) {
      case filterTypes.categories:
        addNewFilter(setSelectedCategories, selectedCategories, filter);
        break;

      case filterTypes.languages:
        addNewFilter(setSelectedLanguages, selectedLanguages, filter);
        break;

      case filterTypes.years:
        addNewFilter(setSelectedYears, selectedYears, filter);
        break;

      default:
        break;
    }
  };

  const removeAFilter = (filterSetter, filterToRemove) => {
    filterSetter((previous) => previous.filter((filter) => filter !== filterToRemove));
  };

  const removeFilter = (filter, filterType) => {
    // remove all filters
    if (filter === null && filterType === null) {
      setSelectedCategories([]);
      setSelectedLanguages([]);
      setSelectedYears([]);
    }

    switch (filterType) {
      case filterTypes.categories:
        removeAFilter(setSelectedCategories, filter);
        break;

      case filterTypes.languages:
        removeAFilter(setSelectedLanguages, filter);
        break;

      case filterTypes.years:
        removeAFilter(setSelectedYears, filter);
        break;

      default:
        break;
    }
  };

  const [categoryValue, setCategoryValue] = useState('');

  const handleValueSelect = (e) => {
    addFilter(e.target.value, e.target.name);
    setCategoryValue('');
  };

  return (
    <>
      <div className="flex flex-col items-start [&>div]:w-full">
        <p className="ml-1">Search Filters</p>

        <div className="flex flex-col gap-5 bg-profileBackground p-4 rounded-md mb-1">
          <div className="flex items-start">
            {/* Availability filter */}
            <div className="flex items-center gap-2 *:cursor-pointer">
              <label htmlFor="availability">Available:</label>
              <input type="checkbox" id="availability" name="availability" />
            </div>
          </div>

          <div className="flex flex-col [&>div>label]:mb-3 [&>div>label]:flex [&>div>label]:flex-col [&>div>label]:gap-1 [&>div>label]:items-start [&>div>label>select]:bg-filter [&>div>label>select]:w-full [&>div>label>select]:rounded-sm [&>div>label>select]:border [&>div>label>select]:border-filterBorder [&>div>label>select]:cursor-pointer">
            {/* Language filter */}
            <div>
              <label htmlFor="language">
                Language
                <select
                  id="language"
                  name="language"
                  value={categoryValue}
                  onChange={handleValueSelect}
                >
                  <option value="" disabled>
                    Select Language
                  </option>
                  {mockLanguages.map((item) => (
                    <option
                      key={item}
                      value={item}
                      disabled={selectedLanguages.includes(item)}
                    >
                      {item}
                    </option>
                  ))}
                </select>
              </label>
            </div>

            {/* Year filter */}
            <div>
              <label htmlFor="year">
                Year
                <select
                  id="year"
                  name="year"
                  value={categoryValue}
                  onChange={handleValueSelect}
                >
                  <option value="" disabled>
                    Select Year
                  </option>
                  {mockYears.map((item) => (
                    <option
                      key={item}
                      value={item}
                      disabled={selectedYears.includes(item.toString())}
                    >
                      {item}
                    </option>
                  ))}
                </select>
              </label>
            </div>

            {/* Category filter */}
            <div>
              <label htmlFor="category">
                Category
                <select
                  id="category"
                  name="category"
                  value={categoryValue}
                  onChange={handleValueSelect}
                >
                  <option value="" disabled>
                    Select Category
                  </option>
                  {mockCategories.map((item) => (
                    <option
                      key={item}
                      value={item}
                      disabled={selectedCategories.includes(item)}
                    >
                      {item}
                    </option>
                  ))}
                </select>
              </label>
            </div>

            <br />
          </div>

          <ActiveFilters
            filters={{
              [filterTypes.categories]: selectedCategories,
              [filterTypes.languages]: selectedLanguages,
              [filterTypes.years]: selectedYears,
            }}
            onRemove={removeFilter}
          />
        </div>
      </div>
    </>
  );
};

export default SearchFilters;
