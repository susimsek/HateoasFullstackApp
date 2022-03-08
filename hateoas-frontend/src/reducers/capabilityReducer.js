import {
  ADD_CAPABILITY,
  CLEAR_CAPABILITY_CLOSE_MODAL,
  DELETE_CAPABILITY,
  GET_CAPABILITIES,
  GET_CAPABILITY, UPDATE_CAPABILITY
} from "../actions/ActionTypes";

const initialState = {
  capabilities: [],
  capability: {},
  links: {},
  page: {
    totalPages: 0,
    totalElements: 0,
    number: 0,
    size: 3
  }
};

export default function(state = initialState, action = {}) {
  switch (action.type) {

    case GET_CAPABILITIES:
      return {
        ...state,
        capabilities: action.payload,
        links: action.links,
        page: action.page
      }

    case DELETE_CAPABILITY:
      return {
        ...state,
        capabilities: state.capabilities.filter(capability => capability.id !== action.payload)
      }

    case ADD_CAPABILITY:
      return {
        ...state,
        capabilities: [action.payload, ...state.capabilities]
      }

    case GET_CAPABILITY:
      return {
        ...state,
        capability: state.capabilities.find(capability => capability.id === action.payload)
      }

    case CLEAR_CAPABILITY_CLOSE_MODAL:
      return {
        ...state,
        capability: action.payload
      }

    case UPDATE_CAPABILITY:
      return {
        ...state,
        capabilities: state.capabilities.map(
            capability => capability.id === action.payload.id ?
                (action.payload) :
                capability)
      }

    default:
      return state;
  }
}