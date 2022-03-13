import React from "react";
import PropTypes from "prop-types";

import {useDispatch} from "react-redux";
import UpdateIcon from "./UpdateIcon";
import {Card, ListGroup} from "react-bootstrap";
import DeleteIcon from "./DeleteIcon";
import { useTranslation } from 'react-i18next';


const Capability = (props) => {
    const { t } = useTranslation('capability');
    const {capability} = props;
    const {
        id,
        techStack,
        numOfDevelopers,
        numOfAvailableDevelopers,
        _links } = capability;
    return (
        <Card border="primary" className="mb-3">
          <Card.Body>
              <h4 className="d-flex text-primary">
                  <div className="me-auto">
                      {techStack} ...{_links.self.href}
                  </div>
                  <div>
                      <UpdateIcon id={id}/>
                      <DeleteIcon id={id} links={_links}/>
                  </div>
              </h4>

              <ListGroup>
                  <ListGroup.Item variant="light" className="text-primary">
                      {t('capability.techStack')}: {techStack}
                  </ListGroup.Item>
                  <ListGroup.Item variant="light" className="text-primary">
                      {t('capability.numOfDevelopers')}: {numOfDevelopers}
                  </ListGroup.Item>
                  <ListGroup.Item variant="light" className="text-primary">
                      {t('capability.numOfAvailableDevelopers')}: {numOfAvailableDevelopers}
                  </ListGroup.Item>
              </ListGroup>
          </Card.Body>
      </Card>
  );
}

Capability.propTypes = {
    capability: PropTypes.object.isRequired
};

export default Capability;
