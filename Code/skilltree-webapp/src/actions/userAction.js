const API_PATH = process.env.REACT_APP_API_URL;

export function setAllUsersAction(users) {
    return {
        type: "users/getAllUsers",
        payload: users,
    };
}

export function fetchAllUsersActionAsync() {
    return async (dispatch) => {
        const options = {
            method: "GET",
            mode: "cors",
        };
        const response = await fetch(`${API_PATH}/users`, options);
        const allUsers = await response.json();
        dispatch(setAllUsersAction(allUsers))
        
        return allUsers.users;
    }
}