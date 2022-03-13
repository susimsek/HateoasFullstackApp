import React, {useState} from 'react';
import {faTimes, faTrash, faTrashCan} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import {Button, Card, Modal} from 'react-bootstrap';
import PropTypes from "prop-types";
import {useDispatch} from "react-redux";
import {
    closeModalClearState,
    deleteCapabilityHandler
} from "../../actions/CapabilityActions";
import {useApiProgress} from "../../shared/ApiProgress";
import ButtonWithProgress from "./ButtonWithProgress";
import { useTranslation } from 'react-i18next';

const customStyles = {
    content: {
        height: "500px"
    }
};

const DeleteIcon = (props) => {

    const { t } = useTranslation(['global', 'capability']);

    const {id, links} = props;

    const pendingApiCall = useApiProgress('delete', links.self.href, true);

    const dispatch = useDispatch();

    const [modalVisible, setModalVisible] = useState(false);

    const openModal = () => {
        setModalVisible(true);
    }

    const closeModal = () => {
        dispatch(closeModalClearState());
        setModalVisible(false);
    }

    const onDeleteClick = async () => {
        try {
            let response = Promise.resolve(dispatch(deleteCapabilityHandler(id, links.self.href)));
            await response;
            closeModal();
        } catch (error) {
        }
    }

    return (
        <>
            <FontAwesomeIcon icon={faTrashCan}
                             color="red"
                             className="ms-2"
                             onClick={openModal} />
            <Modal show={modalVisible}
                   onHide={closeModal}>
                <Modal.Header closeButton>
                    <Modal.Title>{t('capability:capability.home.deleteLabel')}</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Card className="mb-3">
                        <Card.Header className="bg-danger text-light">{t('capability:capability.home.deleteLabel')}</Card.Header>
                        <Card.Body>
                            {t('capability:capability.delete.question')}
                        </Card.Body>
                    </Card>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary"
                            size="lg"
                            onClick={closeModal}
                            disabled={pendingApiCall}>
                        <FontAwesomeIcon icon={faTimes} /> {t('global.entity.action.cancel')}
                    </Button>
                    <ButtonWithProgress
                            variant="danger"
                            size="lg"
                            onClick={onDeleteClick}
                            pendingApiCall={pendingApiCall}
                            disabled={pendingApiCall}>
                        <FontAwesomeIcon icon={faTrash} /> {t('global.entity.action.delete')}
                    </ButtonWithProgress>
                </Modal.Footer>
            </Modal>
        </>
    );
};

DeleteIcon.propTypes = {
    id: PropTypes.number.isRequired
};

export default DeleteIcon;