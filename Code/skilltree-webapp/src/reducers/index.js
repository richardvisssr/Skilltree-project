import { combineReducers } from 'redux';
import skilltreeReducer from '../reducers/skilltreeReducer';
import nodeReducer from '../reducers/nodeReducer';

const reducers = combineReducers({
  skilltree: skilltreeReducer,
  node: nodeReducer,
});

export default reducers;
