const initialState = {
  skilltrees: [],
  title: '',
  description: '',
  skilltreeSelected: false,
};

function skillTreeReducer(state = initialState, action) {
  switch (action.type) {
    case 'skilltree/setSkilltrees':
      return {
        ...state,
        skilltrees: action.payload
      }
    case 'skilltree/createSkilltree':
      return {
        ...state,
        title: action.payload.title,
        description: action.payload.description
      }
    case 'skilltree/setSkilltreeSlice':
      return {
        ...state,
        skilltreeSelected: action.payload
      };
    default:
      return state;
  }
}
export default skillTreeReducer;