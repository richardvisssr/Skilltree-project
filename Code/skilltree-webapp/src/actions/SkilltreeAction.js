const API_PATH = process.env.REACT_APP_API_URL;

export function setSkilltreesAction(skilltrees) {
    return {
        type: "skilltree/setSkilltrees",
        payload: skilltrees,
    };
}

export function fetchAllSkilltreesActionAsync(userId) {
    return async (dispatch) => {
        const options = {
            method: "GET",
            mode: "cors",
        };
        fetch(`${API_PATH}/skilltrees/docenten/${userId}`, options)
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

export function fetchCreateSkillTreeActionAsync(title, description, userId) {
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
        const result = await fetch(`${API_PATH}/skilltrees/docenten/${userId}`, options);
        dispatch(fetchAllSkilltreesActionAsync(userId));
        return result;
    };
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
