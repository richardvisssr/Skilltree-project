import { combineReducers } from 'redux';
import skilltreeReducer from '../reducers/skilltreeReducer';

const reducers = combineReducers({
  skilltree: skilltreeReducer,
});

export default reducers;
