import React from "react";
import {useNavigate} from "react-router-dom";
import {Button} from "react-bootstrap";
import { useTranslation } from 'react-i18next';

const Landing = () => {
  const navigate = useNavigate();
  const { t } = useTranslation('home');

  return (
      <div className="landing">
        <div className="light-overlay landing-inner text-dark">
          <div className="container">
            <div className="row">
              <div className="col-md-12 text-center">
                <h1 className="display-3 mb-4">{t('home.title')}</h1>
                <hr />
                <Button variant="primary"
                        className="me-2"
                        size="lg"
                        onClick={() => navigate('/dashboard')}>
                  {t('home.dashboardLabel')}
                </Button>
              </div>
            </div>
          </div>
        </div>
      </div>
  );
}

export default Landing;
