import { combineReducers } from "redux";
import skilltreeReducer from "./docentRedusers/skilltreeReducerDocent";
import createNodeReducer from "./nodeReducer";
import userReducer from "./userReducer";
import studentsReduces from "./studentsReducer";

const reducers = combineReducers({
    skilltree: skilltreeReducer,
    node: createNodeReducer,
    user: userReducer,
    student: studentsReduces,
});

export default reducers;
