import {GET_ERRORS} from "../actions/ActionTypes";

const initialState = {};

export default (state = initialState, action = {}) => {
    if (action.type === GET_ERRORS) {
        return action.payload;
    } else {
        return state;
    }
}