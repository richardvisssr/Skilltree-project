const initialState = {
  skilltrees: [],
  skilltreeSelected: false
};

function skilltreeReducer(state = initialState, action) {
  switch(action.type) {
    case 'skilltree/setSkilltrees':
      return {
        ...state,
        skilltrees: action.payload
      };
    case 'skilltree/setSkilltreeSlice':
      return {
        ...state,
        skilltreeSelected: action.payload
      };
    default:
      return state;
  }
}

export default skilltreeReducer;