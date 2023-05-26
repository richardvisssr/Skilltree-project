const initialState = {
  userId: 1,
  roleId: 1,
  // TODO: veranderen naar nulls wanneer inloggen werkend is
}

function userReducer(state = initialState, action) {
  switch (action.type) {
  default:
      return state;
  }
}
export default userReducer;
