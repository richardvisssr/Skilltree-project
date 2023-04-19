import { combineReducers } from 'redux';
import testReducer from './testReducer';
import '../styles/styles.css'


const reducers = combineReducers({
  test: testReducer
});

export default reducers;
