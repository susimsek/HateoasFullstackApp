import React from 'react';
import {NavDropdown} from 'react-bootstrap';
import { useTranslation } from 'react-i18next';
import Language from './Language';
import {changeAcceptLanguage} from "../../api/apiCalls";

const languages = [
    {
        locale: 'en',
        countryCode: 'US'
    },
    {
        locale: 'tr',
        countryCode: 'TR'
    }
];

const LanguageSelector = () => {
    const { t, i18n } = useTranslation();

    const changeLanguage = language => {
        i18n.changeLanguage(language);
        changeAcceptLanguage(language);
    }
    
    return (
        <div>
            <NavDropdown align="end" title={t('global.menu.language.main')} className="bg-primary">
                {languages.map(language => <Language
                              key={language.locale}
                              language={language}
                              currentLanguage={i18n.language}
                              label={t(`global.menu.language.${language.locale}`)}
                              onClick={() => changeLanguage(language.locale)}/>)}
            </NavDropdown>
        </div>
    );
};

export default LanguageSelector;