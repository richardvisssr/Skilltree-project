import { combineReducers } from "redux";
import createNodeReducer from "./nodeReducer";
import deleteNodeCardReducer from "./nodeCardReducer";

const reducers = combineReducers({
    Node: createNodeReducer,
    NodeCard: deleteNodeCardReducer,
});

export default reducers;
