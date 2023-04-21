const initialState = {
  skilltrees: [],
  title: '',
  description: ''
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
    default:
      return state;
  }
}
export default skillTreeReducer;