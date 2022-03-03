import React from "react";
import {Container, Nav, Navbar} from "react-bootstrap";
import logo from '../../assets/logo.png';

const Header = () => {
  return (
      <Navbar bg="primary"
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
              <Nav className="me-auto">
                  <Nav.Link href="/">Home</Nav.Link>
              </Nav>
          </Container>
      </Navbar>
  );
}

export default Header;
