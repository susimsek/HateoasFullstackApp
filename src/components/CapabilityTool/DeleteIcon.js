import React, {useState} from 'react';
import {faTimes, faTrash, faTrashCan, faUserEdit, faUserTimes} from "@fortawesome/free-solid-svg-icons";
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import UpdateCapability from "./UpdateCapability";
import {Button, Card, Modal} from 'react-bootstrap';
import PropTypes from "prop-types";
import {useDispatch} from "react-redux";
import {
    addCapabilityHandler,
    closeModalClearState,
    deleteCapabilityHandler,
    getErrors
} from "../../actions/CapabilityActions";
import AddCapability from "./AddCapability";
import {useApiProgress} from "../../shared/ApiProgress";
import ButtonWithProgress from "./ButtonWithProgress";

const customStyles = {
    content: {
        height: "500px"
    }
};

const DeleteIcon = (props) => {

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
            await dispatch(deleteCapabilityHandler(id, links.self.href));
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
                    <Modal.Title>Delete Capability</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <Card className="mb-3">
                        <Card.Header className="bg-danger text-light">Delete Capability</Card.Header>
                        <Card.Body>
                            Are you sure to delete this capability?
                        </Card.Body>
                    </Card>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="secondary"
                            size="lg"
                            onClick={closeModal}
                            disabled={pendingApiCall}>
                        <FontAwesomeIcon icon={faTimes} /> Cancel
                    </Button>
                    <ButtonWithProgress
                            variant="danger"
                            size="lg"
                            onClick={onDeleteClick}
                            pendingApiCall={pendingApiCall}
                            disabled={pendingApiCall}>
                        <FontAwesomeIcon icon={faTrash} /> Delete
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