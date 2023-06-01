const initialState = {
  users: [],
  // TODO: veranderen naar nulls wanneer inloggen werkend is
}

function userReducer(state = initialState, action) {
  switch (action.type) {
    case "users/setAllUsers":
      return {
        ...state,
        users: action.payload.accounts,
      };
  default:
      return state;
  }
}
export default userReducer;
