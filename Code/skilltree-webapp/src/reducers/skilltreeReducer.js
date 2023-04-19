const initialState = {
  skilltrees: []
};

function skilltreeReducer(state = initialState, action) {
  switch(action.type) {
    case 'skilltree/setSkilltrees':
      return {
        ...state,
        skilltrees: action.payload
      };
    default:
      return state;
  }
}

export default skilltreeReducer;