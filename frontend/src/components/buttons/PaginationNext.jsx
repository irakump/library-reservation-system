import { useLayoutDirection } from '../../contexts/LayoutDirectionContext.jsx';
import ChevronLeft from '@heroicons/react/24/solid/ChevronLeftIcon';
import ChevronRight from '@heroicons/react/24/solid/ChevronRightIcon';

export default function PaginationNext({
  setCurrentPage,
  scrollToSearchResultsHeader,
  pagesToShow,
}) {
  const { isRTL } = useLayoutDirection();

  return (
    <button
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
