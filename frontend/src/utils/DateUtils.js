import i18n from "i18next";

export function localizeYear(year) {
    if (!year) return null;
    const date = new Date(year, 0, 1)
    return new Intl.DateTimeFormat(i18n.language, {year: 'numeric'}).format(date)
}

export function localizeDate(date) {
    if (!date) return null;
    return new Intl.DateTimeFormat(i18n.language, {dateStyle: "long"}).format(new Date(date));
}
