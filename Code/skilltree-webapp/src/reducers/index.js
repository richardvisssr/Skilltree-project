import { combineReducers } from "redux";
import skilltreeReducer from "./skilltreeReducer";
import createNodeReducer from "./nodeReducer";

const reducers = combineReducers({
    skilltree: skilltreeReducer,
    node: createNodeReducer,
});

export default reducers;
