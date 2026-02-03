const FilterTag = ({ filterName, onRemove, closeButton = true }) => {
  const closeButtonChar = '✕';

  return (
    <button onClick={onRemove} className="text-xs flex items-center rounded-md p-1 bg-tag border border-tagBorder">
      <span className="flex-1">{filterName}</span>
      {closeButton && <span className="ml-2">{closeButtonChar}</span>}
    </button>
  );
};

export default FilterTag;
