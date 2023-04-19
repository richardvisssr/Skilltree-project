const initialState = {
  title: '',
  description: ''
};

function skillTreeReducer(state = initialState, action) {
  switch (action.type) {
    case 'CREATE_SKILL_TREE':
      console.log(action.payload.title);
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