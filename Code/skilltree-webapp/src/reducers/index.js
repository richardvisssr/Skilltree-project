import { combineReducers } from "redux";
import skilltreeReducer from "./skilltreeReducer";

const reducers = combineReducers({
    skilltree: skilltreeReducer,
});

export default reducers;
