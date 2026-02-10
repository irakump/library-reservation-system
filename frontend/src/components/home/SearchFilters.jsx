import ActiveFilters from './ActiveFilters';
import { useState, useEffect } from 'react';
import axios from 'axios';
import { useSearchResult} from '../../contexts/SearchResultContext.jsx';

const SearchFilters = () => {
  const { setSearchResults } = useSearchResult();

  const [genres, setGenres] = useState([]);
  const [languages, setLanguages] = useState([]);
  const [years, setYears] = useState([]);

  useEffect(() => {
    axios
      .get('http://localhost:8081/api/genre')
      .then((response) => setGenres(response.data))
      .catch((error) => console.error(error));

    axios
      .get('http://localhost:8081/api/language')
      .then((response) => setLanguages(response.data))
      .catch((error) => console.error(error));

    axios
      .get('http://localhost:8081/api/book/years')
      .then((response) => setYears(response.data))
      .catch((error) => console.error(error));
  }, []);

  const filterTypes = {
    genres: 'genre',
    languages: 'language',
    years: 'year',
  };

  // selected filters: variables and methods
  const [selectedGenres, setSelectedGenres] = useState([]);
  const [selectedLanguages, setSelectedLanguages] = useState([]);
  const [selectedYears, setSelectedYears] = useState([]);

  // fetch filtered books when active filters change
  useEffect(() => {
    const fetchFilteredBooks = async () => {
      try {
        const response = await axios.get(
          'http://localhost:8081/api/book/filter',
          {
            params: {
              genres: selectedGenres.length > 0 ? selectedGenres : undefined,
              years: selectedYears.length > 0 ? selectedYears : undefined,
              languages:
                selectedLanguages.length > 0 ? selectedLanguages : undefined,
            },
          },
        );
        
        console.log('Filtered Books:', response.data);
        setSearchResults(response.data);
        
      } catch (error) {
        console.error(error);
      }
    };
    fetchFilteredBooks();
  }, [selectedGenres, selectedYears, selectedLanguages]);

  const addNewFilter = (filterSetter, filters, newFilter) => {
    if (filters.includes(newFilter)) return;
    filterSetter([...filters, newFilter]);
  };

  const addFilter = (filter, filterType) => {
    switch (filterType) {
      case filterTypes.genres:
        addNewFilter(setSelectedGenres, selectedGenres, filter);
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
    filterSetter((previous) =>
      previous.filter((filter) => filter !== filterToRemove),
    );
  };

  const removeFilter = (filter, filterType) => {
    // remove all filters
    if (filter === null && filterType === null) {
      setSelectedGenres([]);
      setSelectedLanguages([]);
      setSelectedYears([]);
    }

    switch (filterType) {
      case filterTypes.genres:
        removeAFilter(setSelectedGenres, filter);
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
                  {languages.map((item) => (
                    <option
                      key={item.language}
                      value={item.language}
                      disabled={selectedLanguages.includes(item.language)}
                    >
                      {item.language}
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
                  {years.map((item) => (
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

            {/* Genre filter */}
            <div>
              <label htmlFor="genre">
                Genre
                <select
                  id="genre"
                  name="genre"
                  value={categoryValue}
                  onChange={handleValueSelect}
                >
                  <option value="" disabled>
                    Select Genre
                  </option>
                  {genres.map((item) => (
                    <option
                      key={item.genre}
                      value={item.genre}
                      disabled={selectedGenres.includes(item.genre)}
                    >
                      {item.genre}
                    </option>
                  ))}
                </select>
              </label>
            </div>

            <br />
          </div>

          <ActiveFilters
            filters={{
              [filterTypes.genres]: selectedGenres,
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
