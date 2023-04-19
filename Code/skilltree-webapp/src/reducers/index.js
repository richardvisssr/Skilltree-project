import { combineReducers } from 'redux';
import reducesSkiltree from './reduces-skiltree';


const reducers = combineReducers({
  create: reducesSkiltree
});

export default reducers;
