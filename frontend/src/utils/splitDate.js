import {parseISO, format} from "date-fns"

export function splitDate(date) {
    const parsedDate = parseISO(date);
    return format(parsedDate, "dd.MM.yyyy.");
}
