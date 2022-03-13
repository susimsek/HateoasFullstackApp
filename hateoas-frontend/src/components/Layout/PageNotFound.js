import React from "react";
import { useTranslation } from 'react-i18next';
import {faExclamation} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {Alert} from "react-bootstrap";

const PageNotFound = () => {
  const { t } = useTranslation('error');
  return (
      <Alert variant="danger" className="text-center">
          <div>
              <FontAwesomeIcon icon={faExclamation}
                               style={{ fontSize: '32px' }}
                               className="ms-2"/>
          </div>
         {t('error.http.404')}
      </Alert>
  );
}

export default PageNotFound;
