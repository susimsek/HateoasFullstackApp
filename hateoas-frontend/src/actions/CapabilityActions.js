import {
    ADD_CAPABILITY,
    CLEAR_CAPABILITY_CLOSE_MODAL,
    DELETE_CAPABILITY,
    GET_CAPABILITIES,
    GET_CAPABILITY,
    GET_ERRORS, UPDATE_CAPABILITY
} from "./ActionTypes";
import {addCapability, deleteCapability, getAllCapabilities, updateCapability} from "../api/apiCalls";


export const clearErrors = () => {
    return {
        type: GET_ERRORS,
        payload: {}
    }
};

export const getErrors = (payload) => {
    return {
        type: GET_ERRORS,
        payload: payload
    }
};

export const getAllCapabilitiesHandler = (getLink) => async dispatch => {
    try {
        const res = await getAllCapabilities(getLink);
        dispatch({
            type: GET_CAPABILITIES,
            payload: res.data._embedded ? res.data._embedded.capabilities : [],
            links: res.data._links,
            page: res.data.page
        });
    } catch (error) {
        dispatch({
            type: GET_CAPABILITIES,
            payload: [],
            links: {},
            page: {}
        });
    }
}

export const deleteCapabilityHandler = (id, deleteLink) => async dispatch => {
    await deleteCapability(deleteLink);
    dispatch({
        type: DELETE_CAPABILITY,
        payload: id
    });
}

export const addCapabilityHandler = (capability, postLink) => async dispatch => {
    const res = await addCapability(capability, postLink);
    dispatch({
        type: ADD_CAPABILITY,
        payload: res.data
    });
    dispatch(clearErrors());
    return res;
}

export const getCapabilityHandler = id => async dispatch => {
    dispatch({
        type: GET_CAPABILITY,
        payload: id
    });
}

export const closeModalClearState = () => async dispatch => {
    dispatch({
        type: CLEAR_CAPABILITY_CLOSE_MODAL,
        payload: {
            techStack: "",
            numOfDevelopers: 0,
            numOfAvailableDevelopers: 0,
        }
    });
    dispatch(clearErrors());
    dispatch(getAllCapabilitiesHandler());
}

export const updateCapabilityHandler = (capability, updateLink) => async dispatch => {
    const res = await updateCapability(capability, updateLink);
    dispatch({
        type: UPDATE_CAPABILITY,
        payload: res.data
    });
    dispatch(clearErrors());
    return res;
}