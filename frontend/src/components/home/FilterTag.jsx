import XMark from '@heroicons/react/24/solid/XMarkIcon';

const FilterTag = ({ filterName, onRemove, closeButton = true }) => {
  return (
    <button
      onClick={onRemove}
      className="text-xs flex items-center rounded-md p-1 bg-tag border border-tagBorder cursor-pointer"
    >
      <span className="flex-1">{filterName}</span>
      {closeButton && <XMark className="ml-1 size-4" />}
    </button>
  );
};

export default FilterTag;
