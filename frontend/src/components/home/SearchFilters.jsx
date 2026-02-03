import ActiveFilters from './ActiveFilters';

const SearchFilters = () => {
  return (
    <>
      <div className="flex flex-col items-start [&>div]:w-full">
        <p className="ml-1">Search Filters</p>

        <div className="flex flex-col gap-5 bg-profileBackground p-4 rounded-md mb-1">
          <div className="flex items-start">
            {/* Availability filter */}
            <div className="flex items-center gap-2">
              <label htmlFor="availability">Available:</label>
              <input type="checkbox" id="availability" name="availability" />
            </div>
          </div>

          <div className="flex flex-col [&>div>label]:mb-3 [&>div>label]:flex [&>div>label]:flex-col [&>div>label]:gap-1 [&>div>label]:items-start [&>div>label>select]:bg-filter [&>div>label>select]:w-full [&>div>label>select]:rounded-sm [&>div>label>select]:border [&>div>label>select]:border-filterBorder">
            {/* Language filter */}
            <div>
              <label htmlFor="language">
                Language
                <select id="language" name="language">
                  <option value=""></option>
                  <option value="english">English</option>
                  <option value="finnish">Finnish</option>
                </select>
              </label>
            </div>

            {/* Year filter */}
            <div>
              <label htmlFor="year">
                Year
                <select id="year" name="year">
                  <option value=""></option>
                  <option value="2020">2020</option>
                  <option value="2021">2021</option>
                  <option value="2022">2022</option>
                  <option value="2023">2023</option>
                  <option value="2024">2024</option>
                  <option value="2025">2025</option>
                  <option value="2026">2026</option>
                </select>
              </label>
            </div>

            {/* Category filter */}
            <div>
              <label htmlFor="category">
                Category
                <select id="category" name="category">
                  <option value=""></option>
                  <option value="fiction">Fiction</option>
                  <option value="non-fiction">Non-Fiction</option>
                  <option value="science">Science</option>
                  <option value="history">History</option>
                </select>
              </label>
            </div>

            <br />
          </div>

          <ActiveFilters />
        </div>
      </div>
    </>
  );
};

export default SearchFilters;
