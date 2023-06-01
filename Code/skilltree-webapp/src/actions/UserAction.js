const API_PATH = process.env.REACT_APP_API_URL;

export function setAllUsersAction(users) {
    return {
        type: "users/setAllUsers",
        payload: users,
    };
}

export function fetchAllUsersActionAsync() {
    return async () => {
        const options = {
            method: "GET",
            mode: "cors",
        };
        const response = await fetch(`${API_PATH}/users`, options);
        const allUsers = await response.json();
        return allUsers.users;
    }
}

function isUserUnique(newEmail, allUsers) {
    let isUserUnique = true;
    allUsers.map(user => {
        if(user.email === newEmail) {
            isUserUnique = false;
        }
    })
    return isUserUnique;
}

function userRegisteredAction(value) {
    return {
        type: "user/userRegistered",
        payload: value,
    }
}

export function fetchRegisterNewUserAsyncAction(user) {
    return async (dispatch) => {
        const allUsers = await dispatch(fetchAllUsersActionAsync());
        if (isUserUnique(user.email, allUsers)) {
            const options = {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                mode: "cors",
                body: JSON.stringify(user),
            };

            fetch(`${API_PATH}/users`, options)
                .then(dispatch(userRegisteredAction(true)))
        } else {
            dispatch(userRegisteredAction(false))
        }
    }
}
