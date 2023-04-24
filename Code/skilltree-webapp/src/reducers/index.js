import { combineReducers } from "redux";
import createNodeReducer from "./nodeReducer";

const reducers = combineReducers({
    Node: createNodeReducer,
});

export default reducers;
