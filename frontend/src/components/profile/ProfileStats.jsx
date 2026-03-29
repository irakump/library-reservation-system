import { useTranslation } from "react-i18next";
import { useFavoritesContext } from "../../contexts/FavoritesContext";
import { useLoanContext } from "../../contexts/LoanContext";
import { useReservationContext } from "../../contexts/ReservationContext";

const ProfileStats = () => {
  const { loans } = useLoanContext();
  const { reservations } = useReservationContext();
  const { favorites } = useFavoritesContext();
  const { t } = useTranslation("profile");

  const stats = [
    { id: "loans", label: t("my_page.current_loans"), value: loans.length },
    {
      id: "reservations",
      label: t("my_page.current_reservations"),
      value: reservations.length,
    },
    {
      id: "favorites",
      label: t("my_page.current_favorites"),
      value: favorites.length,
    },
  ];

  return (
    <div className="bg-white rounded-lg shadow-sm h-fit">
      <div className=" divide-y-2 divide-blue-200">
        {stats.map((info) => (
          <div
            key={info.id}
            className="flex gap-4 py-3 sm:py-4 items-center px-4 sm:px-6"
          >
            <span className="w-36 sm:w-44 text-sm sm:text-base font-semibold text-start ms-4">
              {info.label}
            </span>
            <span className="text-sm sm:text-base font-medium">
              {info.value}
            </span>
          </div>
        ))}
      </div>
    </div>
  );
};

export default ProfileStats;
