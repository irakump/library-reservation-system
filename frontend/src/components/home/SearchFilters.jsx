import ActiveFilters from "./ActiveFilters";
import { useState, useEffect } from "react";
import axios from "axios";
import { useSearchResult } from "../../contexts/SearchResultContext.jsx";
import { useSearchFilters } from "../../contexts/SearchFilterContext.jsx";
import { useTranslation } from "react-i18next";
import i18n from "../../i18n.js";
import { localizeYear } from "../../utils/DateUtils.js";

const SearchFilters = () => {
  // context
  const { fetchSearchResults } = useSearchResult();
  const { searchFilters, setSearchFilters } = useSearchFilters();
  const { t } = useTranslation("search");

  // data from database
  const [genres, setGenres] = useState([]);
  const [languages, setLanguages] = useState([]);
  const [years, setYears] = useState([]);

  useEffect(() => {
    axios
      .get(`http://localhost:8081/api/genre/all/${i18n.language}`)
      .then((response) => setGenres(response.data))
      .catch((error) => console.error(error));

    axios
      .get(`http://localhost:8081/api/language/all/${i18n.language}`)
      .then((response) => setLanguages(response.data))
      .catch((error) => console.error(error));

    axios
      .get("http://localhost:8081/api/book/years")
      .then((response) => setYears(response.data))
      .catch((error) => console.error(error));
  }, [i18n.language]);

  const filterTypes = {
    genre: "genre",
    language: "language",
    years: "years",
  };

  // option to limit genre and language filters for one per category
  const allowMultipleGenresSearch = false;
  const allowMultipleLanguagesSearch = false;

  // selected filters: variables and methods
  const [selectedGenres, setSelectedGenres] = useState([]);
  const [selectedLanguages, setSelectedLanguages] = useState([]);
  const [selectedYears, setSelectedYears] = useState([]);
  const [available, setAvailable] = useState(true);

  // fetch filtered books when active filters change
  useEffect(() => {
    const handleFilterChange = () => {
      setSearchFilters({
        ...searchFilters,
        genre: selectedGenres,
        language: selectedLanguages,
        years: selectedYears,
        available: available,
      });
    };
    handleFilterChange();
  }, [selectedGenres, selectedLanguages, selectedYears, available]);

  useEffect(() => {
    fetchSearchResults();
  }, [searchFilters, i18n.language]);

  const addNewFilter = (filterSetter, filters, newFilter, multipleFilters) => {
    if (filters.includes(newFilter)) return;
    if (multipleFilters) {
      filterSetter([...filters, newFilter]);
    } else {
      filterSetter([newFilter]);
    }
  };

  const addFilter = (filter, filterType) => {
    switch (filterType) {
      case filterTypes.genre:
        addNewFilter(
          setSelectedGenres,
          selectedGenres,
          filter,
          allowMultipleGenresSearch,
        );
        break;

      case filterTypes.language:
        addNewFilter(
          setSelectedLanguages,
          selectedLanguages,
          filter,
          allowMultipleLanguagesSearch,
        );
        break;

      case filterTypes.years:
        addNewFilter(setSelectedYears, selectedYears, Number(filter), true);
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
      case filterTypes.genre:
        removeAFilter(setSelectedGenres, filter);
        break;

      case filterTypes.language:
        removeAFilter(setSelectedLanguages, filter);
        break;

      case filterTypes.years:
        removeAFilter(setSelectedYears, filter);
        break;

      default:
        break;
    }
  };

  const [categoryValue, setCategoryValue] = useState("");

  const handleValueSelect = (e) => {
    addFilter(e.target.value, e.target.name);
    setCategoryValue("");
  };

  return (
    <div className="flex flex-col items-start [&>div]:w-full">
      <p className="px-4 sm:px-1 mb-2">{t("filters.title")}</p>

      <div className="flex flex-col gap-5 bg-profileBackground p-4 sm:rounded-md mb-1">
        <div className="flex items-start">
          {/* Availability filter */}
          <div className="flex items-center gap-2 *:cursor-pointer max-w-full">
            <label htmlFor="availability">{t("filters.availability")}</label>
            <input
              type="checkbox"
              id="availability"
              name="availability"
              checked={available}
              onChange={(e) => setAvailable(e.target.checked)}
            />
          </div>
        </div>

        <div className="flex flex-col gap-4 sm:flex-row sm:gap-14 [&>div]:sm:flex-1 [&>div>label]:mb-3 [&>div>label]:flex [&>div>label]:flex-col [&>div>label]:gap-1 [&>div>label]:items-start [&>div>label>select]:bg-actionButton [&>div>label>select]:rounded-sm [&>div>label>select]:border [&>div>label>select]:border-filterBorder [&>div>label>select]:cursor-pointer [&>div>label>select]:w-60 sm:[&>div>label>select]:w-full [&>div>label>select]:max-w-full">
          {/* Language filter */}
          <div>
            <label htmlFor="language">
              {t("filters.language")}
              <select
                className={"capitalize"}
                id="language"
                name="language"
                data-testid="language-select"
                value={categoryValue}
                onChange={handleValueSelect}
              >
                <option value="" disabled>
                  {t("filters.language_placeholder")}
                </option>
                {languages.map((item) => (
                  <option
                    className={"capitalize"}
                    key={item.languageKey}
                    value={item.languageKey}
                    disabled={selectedLanguages.includes(item.languageKey)}
                  >
                    {item.language}
                  </option>
                ))}
              </select>
            </label>
          </div>

          {/* Genre filter */}
          <div>
            <label htmlFor="genre">
              {t("filters.genre")}
              <select
                className={"capitalize"}
                id="genre"
                name="genre"
                data-testid="genre-select"
                value={categoryValue}
                onChange={handleValueSelect}
              >
                <option value="" disabled>
                  {t("filters.genre_placeholder")}
                </option>
                {genres.map((item) => (
                  <option
                    className={"capitalize"}
                    key={item.genreKey}
                    value={item.genreKey}
                    disabled={selectedGenres.includes(item.genreKey)}
                  >
                    {item.genre}
                  </option>
                ))}
              </select>
            </label>
          </div>

          {/* Year filter */}
          <div>
            <label htmlFor="year">
              {t("filters.year")}
              <select
                id="year"
                name="years"
                data-testid="year-select"
                value={categoryValue}
                onChange={handleValueSelect}
              >
                <option value="" disabled>
                  {t("filters.year_placeholder")}
                </option>
                {years
                  .toSorted((a, b) => b - a)
                  .map((item) => (
                    <option
                      key={item}
                      value={item}
                      disabled={selectedYears.includes(item)}
                    >
                      {localizeYear(item)}
                    </option>
                  ))}
              </select>
            </label>
          </div>
        </div>

        <ActiveFilters
          filters={{
            [filterTypes.genre]: selectedGenres,
            [filterTypes.language]: selectedLanguages,
            [filterTypes.years]: selectedYears,
          }}
          genres={genres}
          languages={languages}
          onRemove={removeFilter}
        />
      </div>
    </div>
  );
};

export default SearchFilters;
