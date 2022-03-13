import React from "react";
import {Container, Nav, Navbar} from "react-bootstrap";
import logo from '../../assets/logo.png';
import LanguageSelector from '../Language/LanguageSelector';
import { useTranslation } from 'react-i18next';

const Header = () => {
    const { t } = useTranslation();
  return (
      <Navbar
              collapseOnSelect
              bg="primary"
              variant="dark"
              expand ="sm"
              className="mb-3 py-0">
          <Container>
              <Navbar.Brand href="/">
                  <img
                      alt="logo"
                      src={logo}
                      width="30"
                      height="30"
                      className="d-inline-block align-top"
                  />{' '}
                  Hateoas
              </Navbar.Brand>
              <Navbar.Toggle className="m-2"/>
              <Navbar.Collapse id="responsive-navbar-nav">
                  <Nav className="me-auto">
                      <Nav.Link href="/">{t('global.menu.home')}</Nav.Link>
                  </Nav>
                  <Nav>
                      <LanguageSelector/>
                  </Nav>
              </Navbar.Collapse>
          </Container>
      </Navbar>
  );
}

export default Header;
