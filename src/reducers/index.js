import { combineReducers } from "redux";
import capabilityReducer from "./capabilityReducer";
import errorReducer from "./errorReducer";

export default combineReducers({
  capability: capabilityReducer,
  errors: errorReducer
});
