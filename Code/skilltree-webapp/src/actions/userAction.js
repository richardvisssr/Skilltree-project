const API_PATH = process.env.REACT_APP_API_URL;

export function setAllUsersAction(users) {
    return {
        type: "users/setAllUsers",
        payload: users,
    };
}

export function fetchAllUsersActionAsync() {
    return async (dispatch) => {
        const options = {
            method: "GET",
            mode: "cors",
        };
        const response = await fetch(`${API_PATH}/accounts`, options);
        const allUsers = await response.json();
        dispatch(setAllUsersAction(allUsers))
        return allUsers.users;
    }
}

export function currentUserSelectedAction(currentUser) {
    return {
        type: "users/setUsers",
        payload: currentUser,
    };
}