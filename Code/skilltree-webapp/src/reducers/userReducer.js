const initialState = {
  users: [],
  registeredCorrectly: null
}

function userReducer(state = initialState, action) {
    switch (action.type) {
        case "user/reset":
            return {
                ...state,
                users: [],
                registeredCorrectly: null
            }
      case "user/userRegistered":
          return {
              ...state,
              registeredCorrectly: action.payload
          };
      case "users/setAllUsers":
        return {
          ...state,
          users: action.payload.users,
        };
    default:
        return state;
  }
}
export default userReducer;
