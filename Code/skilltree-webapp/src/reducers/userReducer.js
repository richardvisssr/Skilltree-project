const initialState = {
  users: [],
  currentUser:[]
  // TODO: veranderen naar nulls wanneer inloggen werkend is
}

function userReducer(state = initialState, action) {
  switch (action.type) {
    case "users/setAllUsers":
      return {
        ...state,
        users: action.payload.accounts,
      };
    case "users/setUsers":
      return {
        ...state,
        currentUser: action.payload.accounts,
      };
  default:
      return state;
  }
}
export default userReducer;
