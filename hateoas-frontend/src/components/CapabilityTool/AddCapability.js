import React, {useEffect, useState} from "react";
import {useDispatch, useSelector} from "react-redux";
import {addCapabilityHandler, getErrors} from "../../actions/CapabilityActions";
import Input from "../Input/Input";
import {Card} from "react-bootstrap";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faSave} from "@fortawesome/free-solid-svg-icons";
import ButtonWithProgress from "./ButtonWithProgress";
import {useApiProgress} from "../../shared/ApiProgress";
import { useTranslation } from 'react-i18next';

const AddCapability = (props) => {

    const dispatch = useDispatch();
    const { t } = useTranslation(['global', 'capability']);

    const [form, setForm] = useState({
        techStack: "",
        numOfDevelopers: 0,
        numOfAvailableDevelopers: 0,
        errors: {}
    });


    const { errors } = useSelector((store) => ({
        errors: store.errors
    }));

    const {postLink} = useSelector((store) => ({
        postLink:  store.capability.links.capabilities.href
    }));

    const pendingApiCall = useApiProgress('post', postLink, true);

    useEffect(() => {
        setForm(prevForm => ({...prevForm, errors: errors}));
    }, [errors]);

    const {closeModal} = props;

    const onChange = event => {
        const {name, value} = event.target;
        form.errors[name] = undefined
        setForm(prevForm => ({...prevForm, [name]: value}));
    }

    const onSubmit = async e => {
        e.preventDefault();

        const newCapability = {
            techStack,
            numOfDevelopers,
            numOfAvailableDevelopers
        };
        try {
            let response = Promise.resolve(dispatch(addCapabilityHandler(newCapability, postLink)));
            await response;
            closeModal();
        } catch (error) {
            dispatch(getErrors(error.response.data));
        }
    }

    const {techStack, numOfDevelopers, numOfAvailableDevelopers, errors: validationErrors} = form;

    const { techStack: techStackError, numOfDevelopers: numOfDevelopersError, numOfAvailableDevelopers: numOfAvailableDevelopersError} = validationErrors;

  return (
      <Card className="mb-3">
          <Card.Header className="bg-primary text-light">{t('capability:capability.home.createLabel')}</Card.Header>
          <Card.Body>
              <form onSubmit={onSubmit}>
                  <Input name="techStack"
                         placeholder= {t('capability:capability.techStack')}
                         value={techStack}
                         onChange={onChange}
                         error={techStackError}/>
                  <Input type="number"
                         name="numOfDevelopers"
                         placeholder={t('capability:capability.numOfDevelopers')}
                         value={numOfDevelopers}
                         onChange={onChange}
                         error={numOfDevelopersError}/>
                  <Input type="number"
                         name="numOfAvailableDevelopers"
                         placeholder={t('capability:capability.numOfAvailableDevelopers')}
                         value={numOfAvailableDevelopers}
                         onChange={onChange}
                         error={numOfAvailableDevelopersError}/>
                  <ButtonWithProgress
                      type="submit"
                      variant="primary"
                      size="lg"
                      pendingApiCall={pendingApiCall}
                      disabled={pendingApiCall}>
                      <FontAwesomeIcon icon={faSave} /> {t('global.entity.action.save')}
                  </ButtonWithProgress>
              </form>
          </Card.Body>
      </Card>
  );
}


export default AddCapability;
