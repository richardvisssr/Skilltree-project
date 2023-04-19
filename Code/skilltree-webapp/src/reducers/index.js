import { combineReducers } from 'redux';

import testReducer from './testReducer';
import skilltreeReducer from './skilltreeReducer';

const reducers = combineReducers({
  test: testReducer,
  skilltree: skilltreeReducer
});

export default reducers;
