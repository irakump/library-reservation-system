import { useTranslation } from "react-i18next";
import { useReservationContext } from "../contexts/ReservationContext";
import BookDataPage from "./BookDataPage";

const ReservationPage = () => {
  const { reservations } = useReservationContext();
  //console.log(reservations);
  const { t } = useTranslation("profile");

    if (reservations.length === 0) {
        return <div className="min-h-screen text-center p-10 bg-background">{t("reservations.title_none")}</div>
    }

  return (
    <div className="bg-background py-10 sm:py-20">
      <div className="gap-16 sm:gap-20 mx-auto sm:max-w-4xl sm:px-3">
        {reservations && (
          <BookDataPage
            title={`${t("reservations.title")} (${reservations.length})`}
            books={reservations}
            pageType="reservation"
          />
        )}
      </div>
    </div>
    
  );
};

export default ReservationPage;
