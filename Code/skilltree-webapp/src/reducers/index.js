import { combineReducers } from "redux";
import skilltreeReducer from "./skilltreeReducer";
import createNodeReducer from "./nodeReducer";
import updateNodeReducer from "./nodeReducer";
import userReducer from "./userReducer";

const reducers = combineReducers({
    skilltree: skilltreeReducer,
    Node: createNodeReducer,
    updateNode: updateNodeReducer,
    user: userReducer,
});

export default reducers;
