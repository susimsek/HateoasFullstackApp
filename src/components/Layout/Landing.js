import React from "react";
import {Link, useNavigate} from "react-router-dom";
import {Button} from "react-bootstrap";

const Landing = () => {

  const navigate = useNavigate();

  return (
      <div className="landing">
        <div className="light-overlay landing-inner text-dark">
          <div className="container">
            <div className="row">
              <div className="col-md-12 text-center">
                <h1 className="display-3 mb-4">Introduction To HATEOAS</h1>
                <hr />
                <Button as="input"
                        variant="primary"
                        className="me-2"
                        size="lg"
                        value="Load Dashboard"
                        onClick={() => navigate('/dashboard')}/>
              </div>
            </div>
          </div>
        </div>
      </div>
  );
}

export default Landing;
