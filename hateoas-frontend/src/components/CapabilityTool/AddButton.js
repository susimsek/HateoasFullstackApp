import React, {useState} from "react";

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {faPlusSquare} from "@fortawesome/free-solid-svg-icons";
import AddCapability from "./AddCapability";
import {useDispatch} from "react-redux";
import {closeModalClearState} from "../../actions/CapabilityActions";
import {Button, Modal} from 'react-bootstrap';

const AddButton = () => {
    const dispatch = useDispatch();

    const [modalVisible, setModalVisible] = useState(false);

    const openModal = () => {
        setModalVisible(true);
    }

    const closeModal = () => {
        dispatch(closeModalClearState());
        setModalVisible(false);
    }

    const customStyles = {
        content: {
            height: "500px"
        }
    };

  return (
        <>
            <Button variant="outline-primary"
                    className="mb-3 text-left"
                    onClick={openModal}>
                <FontAwesomeIcon icon={faPlusSquare} /> Add Capability
            </Button>
            <Modal show={modalVisible}
                   onHide={closeModal}
                   dialogClassName={customStyles}>
                <Modal.Header closeButton>
                    <Modal.Title>Add Capability</Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <AddCapability closeModal={closeModal}/>
                </Modal.Body>
            </Modal>
        </>
  );
}

export default AddButton;
