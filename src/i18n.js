import i18n from 'i18next';
import { initReactI18next } from 'react-i18next';
import LanguageDetector from 'i18next-browser-languagedetector';

const fallbackLanguage = ['en'];

const resources = {
    en: {
        global: require("./i18n/en/global.json"),
        capability: require("./i18n/en/capability.json"),
        home: require("./i18n/en/home.json"),
        error: require("./i18n/en/error.json")
    },
    tr: {
        global: require("./i18n/tr/global.json"),
        capability: require("./i18n/tr/capability.json"),
        home: require("./i18n/tr/home.json"),
        error: require("./i18n/tr/error.json")
    }
};

i18n
    .use(LanguageDetector)
    .use(initReactI18next)
    .init({
        resources,
        lng: navigator.language,
        preload: navigator.languages,
        fallbackLng: fallbackLanguage,
        ns: ['global'],
        defaultNS: 'global',
        interpolation: {
            escapeValue: false, // not needed for react as it escapes by default
        }
    });

export default i18n;