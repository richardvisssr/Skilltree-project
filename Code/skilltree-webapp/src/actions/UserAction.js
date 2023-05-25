const API_PATH = process.env.REACT_APP_API_URL;

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

export function fetchRegisterNewUserAsyncAction(user) {
    return async (dispatch) => {
        const allUsers = await dispatch(fetchAllUsersActionAsync());
        if (isUserUnique(user.email, allUsers)) {
            // const options = {
            //     method: "PUT",
            //     headers: {
            //         "Content-Type": "application/json",
            //     },
            //     mode: "cors",
            //     body: JSON.stringify({
            //         "students": studentIds
            //     }),
            // };

            // fetch(`${API_PATH}/users`)
            
        } else {
            console.log("geen kaasje");
        }
    }
}
