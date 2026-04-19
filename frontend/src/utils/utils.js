import i18n from "i18next";

export function localizeYear(year) {
    const date = new Date(year, 0, 1)
    return new Intl.DateTimeFormat(i18n.language, {year: 'numeric'}).format(date)
}
