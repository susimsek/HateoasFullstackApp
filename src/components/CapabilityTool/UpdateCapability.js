import React, {useEffect, useState} from "react";
import {useDispatch, useSelector} from "react-redux";
import {
    getCapabilityHandler, getErrors,
    updateCapabilityHandler
} from "../../actions/CapabilityActions";
import Input from "../Input/Input";
import {Card} from "react-bootstrap";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {faSave} from "@fortawesome/free-solid-svg-icons";
import ButtonWithProgress from "./ButtonWithProgress";
import { useTranslation } from 'react-i18next';

const UpdateCapability = (props) => {
    const dispatch = useDispatch();
    const { t } = useTranslation(['global', 'capability']);

    const [form, setForm] = useState({
        id: "",
        techStack: "",
        numOfDevelopers: 0,
        numOfAvailableDevelopers: 0,
        errors: {}
    });

    const [links, setLinks] = useState({});

    const [pendingApiCall, setPendingApiCall] = useState(false);

    const {id, closeModal} = props;

    const { errors } = useSelector((store) => ({
        errors: store.errors
    }));

    const { capability } = useSelector((store) => ({
        capability: store.capability.capability
    }));

    useEffect(() => {
        dispatch(getCapabilityHandler(id));
    }, [dispatch, id]);

    useEffect(() => {
        setForm(prevForm => ({...prevForm, ...capability}));
        setLinks(capability._links);
    }, [capability]);

    useEffect(() => {
        setForm(prevForm => ({...prevForm, errors: errors}));
    }, [errors]);

    const onChange = event => {
        const {name, value} = event.target;
        form.errors[name] = undefined
        setForm(prevForm => ({...prevForm, [name]: value}));
    }

    const onSubmit = async e => {
        e.preventDefault();

        const updateCapability = {
            id,
            techStack,
            numOfDevelopers,
            numOfAvailableDevelopers
        };
        try {
            setPendingApiCall(true);
            let response = Promise.resolve(dispatch(updateCapabilityHandler(updateCapability, links.self.href)));
            await response;
            setPendingApiCall(false);
            closeModal();
        } catch (error) {
            dispatch(getErrors(error.response.data));
            setPendingApiCall(false);
        }
    }

    const {techStack, numOfDevelopers, numOfAvailableDevelopers, errors: validationErrors} = form;

    const { techStack: techStackError, numOfDevelopers: numOfDevelopersError, numOfAvailableDevelopers: numOfAvailableDevelopersError} = validationErrors;

  return (
      <Card className="mb-3">
          <Card.Header className="bg-primary text-light">{t('capability:capability.home.editLabel')}</Card.Header>
          <Card.Body>
          <form onSubmit={onSubmit}>
              <Input name="techStack"
                     placeholder={t('capability:capability.techStack')}
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

export default UpdateCapability;
