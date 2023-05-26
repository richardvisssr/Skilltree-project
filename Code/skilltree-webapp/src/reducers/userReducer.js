const initialState = {
  userId: 1,
  roleId: 1,
  // TODO: veranderen naar nulls wanneer inloggen werkend is
  registeredCorrectly: null
}

function userReducer(state = initialState, action) {
    switch (action.type) {
        case "user/userRegistered":
            return {
                ...state,
                registeredCorrectly: action.payload
            }
    default:
        return state;
    }
}
export default userReducer;
