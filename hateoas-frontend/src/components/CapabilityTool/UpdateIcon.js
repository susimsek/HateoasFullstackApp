import React, {useState} from 'react';
import {faEdit} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import UpdateCapability from "./UpdateCapability";
import { Modal } from 'react-bootstrap';
import PropTypes from "prop-types";
import {useDispatch} from "react-redux";
import {closeModalClearState} from "../../actions/CapabilityActions";
import { useTranslation } from 'react-i18next';

const customStyles = {
    content: {
        height: "500px"
    }
};

const UpdateIcon = (props) => {

    const dispatch = useDispatch();
    const { t } = useTranslation('capability');

    const [modalVisible, setModalVisible] = useState(false);

    const openModal = () => {
        setModalVisible(true);
    }

    const closeModal = () => {
        dispatch(closeModalClearState());
        setModalVisible(false);
    }

    const {id} = props;

    return (
        <>
            <FontAwesomeIcon icon={faEdit}
                             color="blue"
                             className="ms-2"
                             onClick={openModal}/>
            <Modal show={modalVisible}
                   onHide={closeModal}
                   dialogClassName={customStyles}>
                <Modal.Header closeButton>
                    <Modal.Title>{t('capability:capability.home.editLabel')}</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <UpdateCapability closeModal={closeModal} id={id} />
                </Modal.Body>
            </Modal>
        </>
    );
};

UpdateIcon.propTypes = {
    id: PropTypes.number.isRequired
};

export default UpdateIcon;