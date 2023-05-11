const API_PATH = process.env.REACT_APP_API_URL;

export function setCreateNodeAction(skill, description, positionX, positionY, skilltreeId, learningOutcome, assesmentCriteria) {
    return {
        type: "node/createNode",
        payload: {
            skill,
            description,
            positionX,
            positionY,
            skilltreeId,
            learningOutcome,
            assesmentCriteria: [assesmentCriteria],
        },
    };
}

export const fetchCreateNodeActionAsync = (skill, description, positionX, positionY, assesmentCriteria, learningOutcome, skilltreeId) => async (dispatch) => {
    const options = {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        mode: "cors",
        body: JSON.stringify({
            skill, description, positionX, positionY, skilltreeId, learningOutcome, assesmentCriteria,
        }),

    };
    fetch(`${API_PATH}/nodes/skilltrees/${skilltreeId}`, options)
        .then((response) => response.json())
        .then(() => {
            dispatch(setCreateNodeAction(skill, description, positionX, positionY, skilltreeId, learningOutcome, assesmentCriteria));
        });
};

export function setUpdateNodeAction(skill, description, learningOutcome, assesmentCriteria) {
    return {
        type: "node/updateNode",
            payload: {
                skill,
                description,
                learningOutcome,
                assesmentCriteria: [assesmentCriteria],
        },
    };
 }

export const fetchUpdateNodeActionAsync = (skill, description, learningOutcome, assesmentCriteria) => async (dispatch) => {
    const options = {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
        },
        mode: "cors",
        body: JSON.stringify({
        skill, description, learningOutcome, assesmentCriteria,
        }),

    };
    fetch(`${API_PATH}/nodes`, options)
        .then((response) => response.json())
        .then(() => {
            dispatch(setUpdateNodeAction(skill, description, learningOutcome, assesmentCriteria));
    });
};

export function setHighestNodeIdAction(nodeId) {
    return {
        type: "node/highestNodeId",
        payload: nodeId,
    };
}

export function fetchHighestNodeIdActionAsync() {
    return async (dispatch) => {
        const options = {
            method: "GET",
            mode: "cors",
        };
        fetch(`${API_PATH}/nodes`, options)
            .then((response) => response.json())
            .then((result) => dispatch(setHighestNodeIdAction(result)));
    };
}

export function showCreateCard() {
    return {
        type: "node/showNodeCard"
    };
}
