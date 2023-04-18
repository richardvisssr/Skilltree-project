const initialState = {
  test: null
};

function testReducer(state = initialState, action) {
  switch(action.type) {
    // test voor de '/' is dat het voor de test reducer is, goed voor clean code 
    case 'test/testAction':
      return {
        ...state,
        test: action.payload
      };
    default:
      return state;
  }
}

export default testReducer;