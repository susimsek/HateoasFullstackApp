import React from 'react';
import {NavDropdown} from 'react-bootstrap';
import ReactCountryFlag from 'react-country-flag';

const Language = (props) => {

    const { label, language, onClick, currentLanguage} = props;
    const {locale, countryCode} = language;

    return (
        <NavDropdown.Item onClick={onClick}
                          active={currentLanguage === locale}>
        <ReactCountryFlag countryCode= {countryCode} className="me-2"/>
            {label}</NavDropdown.Item>
    );
};

export default Language;