const API_PATH = process.env.REACT_APP_API_URL;

export function setSkilltreesAction(skilltrees) {
    return {
        type: "skilltree/setSkilltrees",
        payload: skilltrees,
    };
}

export function fetchAllSkilltreesActionAsync(userId, roleId) {
    return async (dispatch) => {
        const options = {
            method: "GET",
            mode: "cors",
        };
        fetch(`${API_PATH}/skilltrees/gebruikers/${userId}/roles/${roleId}`, options)
            .then((response) => response.json())
            .then((result) => dispatch(setSkilltreesAction(result.skilltrees)));
    };
}

export function setCreateSkillTreeAction(title, description) {
    return {
        type: "skilltree/createSkilltree",
        payload: { title, description },
    };
}

export function fetchCreateSkillTreeActionAsync(title, description, userId, roleId) {
    return async (dispatch) => {
        const body = {
            title,
            description,
        };
        const options = {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(body),
            mode: "cors",
        };
        const result = await fetch(`${API_PATH}/skilltrees/gebruikers/${userId}`, options);
        dispatch(fetchAllSkilltreesActionAsync(userId, roleId));
        return result;
    }
}

export function addSkiltreeTopbar() {
    return {
        type: "skilltree/addSkilltreeTopbar",
    };
}

export function setCurrentSkilltreeAction(skilltree) {
    return {
        type: "skilltree/setCurrentSkilltree",
        payload: skilltree,
    };
}

export function setAllNodesFromSkilltree(nodes) {
    return {
        type: "skilltree/setAllNodes",
        payload: nodes,
    }
}

export function fetchAllNodesFromSkilltree(skilltreeId) {
    return async (dispatch) => {
        const options = {
            method: "GET",
            mode: "cors",
        };
        fetch(`${API_PATH}/nodes/skilltrees/${skilltreeId}`, options)
            .then((response) => response.json())
            .then((result) => dispatch(setAllNodesFromSkilltree(result.nodes)));
    };
}

export function fetchUpdateSkillTreeActionAsync(id, title, description, userId, roleId) {
    return async (dispatch) => {
        const body = {
            id,
            title,
            description,
        };
        const options = {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(body),
            mode: "cors",
        };
        const result = await fetch(`${API_PATH}/skilltrees/gebruikers/${userId}`, options);
        dispatch(fetchAllSkilltreesActionAsync(userId, roleId));
        return result;
    }
}

export function showDeleteCard() {
    return {
        type: "skilltree/showDeleteSkilltreeCard",
    };
}

export function hideDeleteCard() {
    return {
        type: "skilltree/hideDeleteSkilltreeCard",
    };
}

export function setDeleteSkilltreeAction(skilltreeId) {
    return {
        type: "skilltree/deleteSkilltree",
        payload: {
            skilltreeId,
        }
    }
}

export const fetchDeleteSkilltreeActionAsync = (skilltreeId) => async (dispatch) => {
    const options = {
        method: "DELETE",
        headers: {
            "Content-Type": "application/json",
        },
        mode: "cors",
        body: JSON.stringify({
            skilltreeId,
        }),
    };

    fetch(`${API_PATH}/skilltrees/delete/${skilltreeId}`, options)
        .then(() => {
            dispatch(setDeleteSkilltreeAction(skilltreeId));
        });
}
