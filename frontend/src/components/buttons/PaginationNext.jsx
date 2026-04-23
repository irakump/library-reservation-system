import { useLayoutDirection } from '../../contexts/LayoutDirectionContext.jsx';
import ChevronLeft from '@heroicons/react/24/solid/ChevronLeftIcon';
import ChevronRight from '@heroicons/react/24/solid/ChevronRightIcon';
import PropTypes from 'prop-types';

export default function PaginationNext({
  setCurrentPage,
  scrollToSearchResultsHeader,
  pagesToShow,
}) {
  const { isRTL } = useLayoutDirection();

  return (
    <button
      aria-label="next page"
      className=":bg-actionButton p-1.5 rounded-md cursor-pointer hover:bg-actionButtonHover"
      key="next-page"
      data-testid="next-page"
      onClick={(e) => {
        e.preventDefault();
        setCurrentPage((previous) => Math.min(previous + 1, pagesToShow));
        scrollToSearchResultsHeader();
      }}
    >
      {isRTL ? (
        <ChevronLeft className="h-full size-5" />
      ) : (
        <ChevronRight className="h-full size-5" />
      )}
    </button>
  );
}

PaginationNext.propTypes = {
  setCurrentPage: PropTypes.func.isRequired,
  scrollToSearchResultsHeader: PropTypes.func.isRequired,
  pagesToShow: PropTypes.number.isRequired,
};
