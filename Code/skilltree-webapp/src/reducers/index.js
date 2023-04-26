import { combineReducers } from "redux";
import skilltreeReducer from "./skilltreeReducer";
import createNodeReducer from "./nodeReducer";
import userReducer from "./userReducer";

const reducers = combineReducers({
    skilltree: skilltreeReducer,
    node: createNodeReducer,
    user: userReducer,
});

export default reducers;
