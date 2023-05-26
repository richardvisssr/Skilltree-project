const initialState = {
  userId: 1,
  roleId: 1,
  users: [],
  // TODO: veranderen naar nulls wanneer inloggen werkend is
}

function userReducer(state = initialState, action) {
  switch (action.type) {
    case "users/getAllUsers":
      return {
        ...state,
        users: action.payload,
      };
  default:
      return state;
  }
}
export default userReducer;
