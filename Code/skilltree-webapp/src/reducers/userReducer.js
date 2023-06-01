const initialState = {
  users: [],
  // TODO: veranderen naar nulls wanneer inloggen werkend is
  registeredCorrectly: null
}

function userReducer(state = initialState, action) {
    switch (action.type) {
        case "user/userRegistered":
            return {
                ...state,
                registeredCorrectly: action.payload
            };
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
