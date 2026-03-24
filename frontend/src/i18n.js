import i18n from 'i18next';
import { initReactI18next } from 'react-i18next';
import Backend from 'i18next-http-backend';
import LanguageDetector from "i18next-browser-languagedetector";

i18n
  .use(Backend)
    .use(LanguageDetector)
  .use(initReactI18next)
  .init({
    fallbackLng: 'en',
    preload: ['en'],

    detection: {
        order: ['localStorage', 'navigator'],
        caches: ['localStorage'],
    },

    backend: { loadPath: '/locales/{{lng}}/{{ns}}.json' },
    ns: [
      'auth',
      'book_card',
      'button',
      'common',
      'navigation',
      'profile',
      'search',
    ],
    defaultNS: 'common',

    interpolation: {
      escapeValue: false,
    },
    react: {
      useSuspense: false,
    },
  });

export default i18n;
