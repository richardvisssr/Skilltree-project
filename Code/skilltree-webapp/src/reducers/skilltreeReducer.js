const initialState = {
  skilltrees: [],
  currentSkilltree: null,
  skilltreeSelected: false,
};

function skillTreeReducer(state = initialState, action) {
  switch (action.type) {
    case 'skilltree/setSkilltrees':
      return {
        ...state,
        skilltrees: action.payload
      }
    case 'skilltree/setCurrentSkilltree':
      return {
        ...state,
        currentSkilltree: action.payload
      }
    default:
      return state;
  }
}
export default skillTreeReducer;