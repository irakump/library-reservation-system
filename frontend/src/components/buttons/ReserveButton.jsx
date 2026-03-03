
//this components is rendered inside BookButton component which gets rendered inside BookModal and BookCard

import { useReservationContext } from "../../contexts/ReservationContext";

function ReserveButton({pageType, book, children}) {
    const {addToReservations, updateReservationStatus} = useReservationContext();

    // New reservation
    return (
        <>
            <p className="text-sm mb-0 text-left"> 🔴 Not available</p>
            <button className="bg-filter font-semibold rounded-xl px-6 py-2 hover:bg-sky-500 float-right cursor-pointer"
                    onClick={e => {
                        e.stopPropagation();
                        addToReservations(book.isbn);
                        {/*here reserve function call in context or something*/}
                    }}>
                {children}
            </button>
        </>
    );
    
} 

export default ReserveButton;