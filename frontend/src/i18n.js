import i18n from 'i18next';
import { initReactI18next } from 'react-i18next';
import authEn from '../public/locales/en/auth.json';
/* import bookCardEn from '../public/locales/en/book_card.json';
import navigationEn from '../public/locales/en/navigation.json';
import profileEn from '../public/locales/en/profile.json';
import searchEn from '../public/locales/en/search.json'; */

const resources = {
  en: {
    auth: authEn,
    /* book_card: bookCardEn,
    navigation: navigationEn,
    profile: profileEn,
    search: searchEn, */
  },
};

i18n
  .use(initReactI18next) // passes i18n down to react-i18next
  .init({
    resources,
    lng: 'en', // language to use, more information here: https://www.i18next.com/overview/configuration-options#languages-namespaces-resources
    // you can use the i18n.changeLanguage function to change the language manually: https://www.i18next.com/overview/api#changelanguage
    // if you're using a language detector, do not define the lng option

    ns: ['auth'],
    defaultNS: 'auth',

    interpolation: {
      escapeValue: false, // react already safes from xss
    },
  });
