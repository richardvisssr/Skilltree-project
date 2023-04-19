import { combineReducers } from 'redux';
import skillTreeReducer from './skillTreeReducer';
import React from 'react';

const reducers = combineReducers({
  skilltree: skillTreeReducer,
});

export default reducers;

